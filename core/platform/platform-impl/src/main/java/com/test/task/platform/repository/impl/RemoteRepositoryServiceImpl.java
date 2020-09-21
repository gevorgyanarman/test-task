package com.test.task.platform.repository.impl;

import com.test.task.github.client.GitHubRepositoriesClient;
import com.test.task.github.client.GitHubSearchClient;
import com.test.task.github.model.search.GitHubRepositorySearchResultResponse;
import com.test.task.github.model.statistics.GitHubContributorsStatisticsResultResponse;
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
class RemoteRepositoryServiceImpl implements RemoteRepositoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteRepositoryServiceImpl.class);
    private static final String SERVICE_NAME = "SEARCH_SERVICE";


    private final GitHubSearchClient gitHubSearchClient;
    private final GitHubRepositoriesClient gitHubRepositoriesClient;
    private final ScheduledExecutorService scheduler;
    private final TimeLimiter timeLimiter;
    private final CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults(SERVICE_NAME);
    private final ThreadPoolBulkhead threadPoolBulkhead = ThreadPoolBulkhead.ofDefaults(SERVICE_NAME);
    private final RateLimiter rateLimiter;

    RemoteRepositoryServiceImpl(final GitHubSearchClient gitHubSearchClient,
                                final GitHubRepositoriesClient gitHubRepositoriesClient,
                                final ResilienceConfiguration resilienceConfiguration) {
        this.gitHubSearchClient = gitHubSearchClient;
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
     * Searches repositories in github for the provided query string
     * As github limited requests count issued to its server, and returns error http status code without expected
     * data, commits retrieval organized with limitations such as rate limier. Also circuit breaker is applied
     * to return result with properly handled error which can be processed by method caller
     *
     * @param query the search query text
     * @return remote result params of repositories
     */
    @Override
    public RemoteResultParams<GitHubRepositorySearchResultResponse> search(final String query) {
        LOGGER.debug("Searching repositories in remote source for the provided query - {}", query);
        Assert.hasText(query, "The repository search query should not be null or empty");
        final RemoteResultParams<GitHubRepositorySearchResultResponse> resultParams = Decorators.ofSupplier(() ->
                RemoteResultParams.ofSuccess(gitHubSearchClient.search(query)))
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
                "Searching repositories in remote source for the provided query - {}, result - {}",
                query,
                resultParams
        );
        return resultParams;
    }

    /**
     * Searches repository contributors in github for the provided repository full name
     * As github limited requests count issued to its server, and returns error http status code without expected
     * data, commits retrieval organized with limitations such as rate limier. Also circuit breaker is applied
     * to return result with properly handled error which can be processed by method caller
     *
     * @param fullName the repository full name
     * @return remote result params of contributors
     */
    @Override
    public RemoteResultParams<GitHubContributorsStatisticsResultResponse> retrieveContributors(final String fullName) {
        LOGGER.debug("Retrieving repository contributors in remote source for the provided full name - {}", fullName);
        Assert.hasText(fullName, "The repository full name should not be null or empty");
        final RemoteResultParams<GitHubContributorsStatisticsResultResponse> resultParams = Decorators.ofSupplier(() ->
                RemoteResultParams.ofSuccess(gitHubRepositoriesClient.retrieveContributors(fullName)))
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
                "Successfully retrieved repository contributors in remote source for the provided full name - {}, result - {}",
                fullName,
                resultParams
        );
        return resultParams;
    }

}
