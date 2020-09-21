package com.test.task.platform.commit.impl

import com.test.task.github.client.GitHubRepositoriesClient
import com.test.task.github.model.commit.GitHubRetrieveCommitsResultResponse
import com.test.task.platform.AbstractPlatformUnitTest
import com.test.task.platform.commons.RemoteResultParams
import com.test.task.platform.configuration.ResilienceConfiguration
import com.test.task.rest.test.helper.external.client.ExternalClientRestTestHelper
import org.assertj.core.api.Assertions.assertThat
import org.easymock.EasyMock.expect
import org.easymock.Mock
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeoutException


class RemoteCommitsRetrievalServiceImplUnitTest : AbstractPlatformUnitTest() {

    private lateinit var remoteCommitsRetrievalService: RemoteCommitsRetrievalService

    @Mock
    private lateinit var gitHubRepositoriesClient: GitHubRepositoriesClient

    private val resilienceConfiguration: ResilienceConfiguration = object : ResilienceConfiguration {
        override fun scheduledThreadPoolSize(): Int = 5

        override fun timeLimiterSeconds(): Int = 5

        override fun rateLimiterRefreshPeriodSeconds(): Int = 5

        override fun rateLimiterPeriod(): Int = 1

        override fun rateLimiterTimeoutDuration(): Int = 1
    }

    private val externalClientRestTestHelper = ExternalClientRestTestHelper()

    @Before
    fun prepare() {
        remoteCommitsRetrievalService = RemoteCommitsRetrievalServiceImpl(gitHubRepositoriesClient, resilienceConfiguration)
    }

    @Test
    fun `test with invalid argument`() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { remoteCommitsRetrievalService.retrieve(null) }
        assertIllegalArgumentException { remoteCommitsRetrievalService.retrieve(emptyString()) }
        verifyAll()
    }

    @Test
    fun `test when rate limit exceeded`() {
        val fullName = uuId()
        val resultResponse = externalClientRestTestHelper.buildGitHubRetrieveCommitsResultResponse()
        resetAll()
        expect(gitHubRepositoriesClient.retrieveCommits(fullName)).andReturn(resultResponse)
        replayAll()
        remoteCommitsRetrievalService.retrieve(fullName)
                .apply { assertThat(this).isEqualTo(RemoteResultParams.ofSuccess(resultResponse)) }
        remoteCommitsRetrievalService.retrieve(fullName)
                .apply { assertThat(this).isEqualTo(RemoteResultParams.ofError<GitHubRetrieveCommitsResultResponse>()) }
        verifyAll()
    }

    @Test
    fun test() {
        val fullName = uuId()
        val resultResponse = externalClientRestTestHelper.buildGitHubRetrieveCommitsResultResponse()
        resetAll()
        expect(gitHubRepositoriesClient.retrieveCommits(fullName)).andReturn(resultResponse)
        replayAll()
        remoteCommitsRetrievalService.retrieve(fullName)
                .apply { assertThat(this).isEqualTo(RemoteResultParams.ofSuccess(resultResponse)) }
        verifyAll()
    }
}