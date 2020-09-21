package com.test.task.platform.repository.impl

import com.test.task.github.client.GitHubRepositoriesClient
import com.test.task.github.client.GitHubSearchClient
import com.test.task.platform.AbstractPlatformUnitTest
import com.test.task.platform.configuration.ResilienceConfiguration
import com.test.task.rest.test.helper.external.client.ExternalClientRestTestHelper
import org.easymock.Mock
import org.junit.Before


abstract class AbstractRemoteRepositoryServiceImplUnitTest : AbstractPlatformUnitTest() {

    internal lateinit var remoteRepositoryService: RemoteRepositoryService

    @Mock
    protected lateinit var gitHubSearchClient: GitHubSearchClient

    @Mock
    protected lateinit var gitHubRepositoriesClient: GitHubRepositoriesClient

    private val resilienceConfiguration: ResilienceConfiguration = object : ResilienceConfiguration {
        override fun scheduledThreadPoolSize(): Int = 5

        override fun timeLimiterSeconds(): Int = 5

        override fun rateLimiterRefreshPeriodSeconds(): Int = 5

        override fun rateLimiterPeriod(): Int = 1

        override fun rateLimiterTimeoutDuration(): Int = 1
    }

    protected val externalClientRestTestHelper = ExternalClientRestTestHelper()

    @Before
    fun prepare() {
        remoteRepositoryService = RemoteRepositoryServiceImpl(gitHubSearchClient, gitHubRepositoriesClient, resilienceConfiguration)
    }
}