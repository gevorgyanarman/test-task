package com.test.task.platform.configuration.impl;

import com.test.task.platform.configuration.ResilienceConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class ResilienceConfigurationImpl implements ResilienceConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResilienceConfigurationImpl.class);

    private final int scheduledThreadPoolSize;
    private final int timeLimiterSeconds;
    private final int rateLimiterRefreshPeriodSeconds;
    private final int rateLimiterPeriod;
    private final int rateLimiterTimeoutDuration;

    ResilienceConfigurationImpl(@Value("${resilience4j.scheduled.thread.pool.size}") final int scheduledThreadPoolSize,
                                       @Value("${resilience4j.time.limiter.seconds}") final int timeLimiterSeconds,
                                       @Value("${resilience4j.rate.limiter.refresh.period.seconds}") final int rateLimiterRefreshPeriodSeconds,
                                       @Value("${resilience4j.rate.limiter.period}") final int rateLimiterPeriod,
                                       @Value("${resilience4j.rate.limiter.timeout.duration}") final int rateLimiterTimeoutDuration) {
        this.scheduledThreadPoolSize = scheduledThreadPoolSize;
        this.timeLimiterSeconds = timeLimiterSeconds;
        this.rateLimiterRefreshPeriodSeconds = rateLimiterRefreshPeriodSeconds;
        this.rateLimiterPeriod = rateLimiterPeriod;
        this.rateLimiterTimeoutDuration = rateLimiterTimeoutDuration;
        LOGGER.debug("Initializing - {}", getClass().getCanonicalName());
    }

    @Override
    public int scheduledThreadPoolSize() {
        return scheduledThreadPoolSize;
    }

    @Override
    public int timeLimiterSeconds() {
        return timeLimiterSeconds;
    }

    @Override
    public int rateLimiterRefreshPeriodSeconds() {
        return rateLimiterRefreshPeriodSeconds;
    }

    @Override
    public int rateLimiterPeriod() {
        return rateLimiterPeriod;
    }

    @Override
    public int rateLimiterTimeoutDuration() {
        return rateLimiterTimeoutDuration;
    }
}
