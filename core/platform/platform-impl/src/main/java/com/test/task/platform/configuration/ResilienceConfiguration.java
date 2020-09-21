package com.test.task.platform.configuration;


public interface ResilienceConfiguration {

    int scheduledThreadPoolSize();

    int timeLimiterSeconds();

    int rateLimiterRefreshPeriodSeconds();

    int rateLimiterPeriod();

    int rateLimiterTimeoutDuration();
}
