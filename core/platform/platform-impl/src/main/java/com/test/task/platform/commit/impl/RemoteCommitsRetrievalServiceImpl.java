package com.test.task.platform.commit.impl;

import com.test.task.github.client.GitHubRepositoriesClient;
import com.test.task.github.model.commit.GitHubRetrieveCommitsResultResponse;
import com.test.task.platform.commons.RemoteResultParams;
import com.test.task.platform.configuration.ResilienceConfiguration;
import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.bulkhead.ThreadPoolBulkhead;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.decorators.Decorators;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.timelimiter.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeoutException;

import static java.util.Arrays.asList;


@Component
class RemoteCommitsRetrievalServiceImpl implements RemoteCommitsRetrievalService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteCommitsRetrievalServiceImpl.class);
    private static final String SERVICE_NAME = "COMMIT_SERVICE";

    private final ScheduledExecutorService scheduler;
    private final TimeLimiter timeLimiter;
    private final CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults(SERVICE_NAME);
    private final ThreadPoolBulkhead threadPoolBulkhead = ThreadPoolBulkhead.ofDefaults(SERVICE_NAME);
    private final RateLimiter rateLimiter;
    private final GitHubRepositoriesClient gitHubRepositoriesClient;

    RemoteCommitsRetrievalServiceImpl(final GitHubRepositoriesClient gitHubRepositoriesClient,
                                      final ResilienceConfiguration resilienceConfiguration) {
        this.gitHubRepositoriesClient = gitHubRepositoriesClient;
        this.scheduler = Executors.newScheduledThreadPool(resilienceConfiguration.scheduledThreadPoolSize());
        this.timeLimiter = TimeLimiter.of(Duration.ofSeconds(resilienceConfiguration.timeLimiterSeconds()));
        this.rateLimiter = RateLimiter.of(
                SERVICE_NAME,
                RateLimiterConfig.custom()
                        .limitRefreshPeriod(Duration.ofSeconds(resilienceConfiguration.rateLimiterRefreshPeriodSeconds()))
                        .limitForPeriod(resilienceConfiguration.rateLimiterPeriod())
                        .timeoutDuration(Duration.ofSeconds(resilienceConfiguration.rateLimiterTimeoutDuration()))
                        .build()
        );
        LOGGER.debug("Initializing - {}", getClass().getCanonicalName());
    }

    /**
     * Retrieves part of commits from github for the provided repository full name
     * As github limited requests count issued to its server, and returns error http status code without expected
     * data, commits retrieval organized with limitations such as rate limier. Also circuit breaker is applied
     * to return result with properly handled error which can be processed by method caller
     *
     * @param fullName the repository full name
     * @return remote result params of remote commits
     */
    @Override
    public RemoteResultParams<GitHubRetrieveCommitsResultResponse> retrieve(final String fullName) {
        LOGGER.debug("Retrieving repository commits in remote source for the provided full name - {}", fullName);
        Assert.hasText(fullName, "The repository full name should not be null or empty");
        final RemoteResultParams<GitHubRetrieveCommitsResultResponse> result = Decorators
                .ofSupplier(() -> RemoteResultParams.ofSuccess(gitHubRepositoriesClient.retrieveCommits(fullName)))
                .withThreadPoolBulkhead(threadPoolBulkhead)
                .withTimeLimiter(timeLimiter, scheduler)
                .withCircuitBreaker(circuitBreaker)
                .withRateLimiter(rateLimiter)
                .withFallback(asList(RequestNotPermitted.class, TimeoutException.class, CallNotPermittedException.class, BulkheadFullException.class),
                        throwable -> {
                            LOGGER.warn("Handling exception with message - {}", throwable.getMessage());
                            return RemoteResultParams.ofError();
                        })
                .get().toCompletableFuture().join();
        LOGGER.debug(
                "Retrieving repository commits in remote source for the provided full name - {}, result - {}",
                fullName,
                result
        );
        return result;
    }
}
