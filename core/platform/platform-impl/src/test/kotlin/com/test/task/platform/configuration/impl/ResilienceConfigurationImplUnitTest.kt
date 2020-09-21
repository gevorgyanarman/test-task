package com.test.task.platform.configuration.impl

import com.test.task.platform.AbstractPlatformUnitTest
import com.test.task.platform.configuration.ResilienceConfiguration
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test


class ResilienceConfigurationImplUnitTest : AbstractPlatformUnitTest() {

    private lateinit var resilienceConfiguration: ResilienceConfiguration

    private val scheduledThreadPoolSize = randomInt()
    private val timeLimiterSeconds = randomInt()
    private val rateLimiterRefreshPeriodSeconds = randomInt()
    private val rateLimiterPeriod = randomInt()
    private val rateLimiterTimeoutDuration = randomInt()

    @Before
    fun prepare() {
        resilienceConfiguration = ResilienceConfigurationImpl(
                scheduledThreadPoolSize,
                timeLimiterSeconds,
                rateLimiterRefreshPeriodSeconds,
                rateLimiterPeriod,
                rateLimiterTimeoutDuration
        )
    }

    @Test
    fun `test scheduled thread pool size`() {
        resetAll()
        replayAll()
        assertThat(resilienceConfiguration.scheduledThreadPoolSize()).isEqualTo(scheduledThreadPoolSize)
        verifyAll()
    }

    @Test
    fun `test time limiter seconds`() {
        resetAll()
        replayAll()
        assertThat(resilienceConfiguration.timeLimiterSeconds()).isEqualTo(timeLimiterSeconds)
        verifyAll()
    }

    @Test
    fun `test rate limiter refresh period seconds`() {
        resetAll()
        replayAll()
        assertThat(resilienceConfiguration.rateLimiterRefreshPeriodSeconds()).isEqualTo(rateLimiterRefreshPeriodSeconds)
        verifyAll()
    }

    @Test
    fun `test rate limiter period`() {
        resetAll()
        replayAll()
        assertThat(resilienceConfiguration.rateLimiterPeriod()).isEqualTo(rateLimiterPeriod)
        verifyAll()
    }

    @Test
    fun `test rate limiter timeout duration`() {
        resetAll()
        replayAll()
        assertThat(resilienceConfiguration.rateLimiterTimeoutDuration()).isEqualTo(rateLimiterTimeoutDuration)
        verifyAll()
    }
}