package com.test.task.platform.repository.impl

import com.test.task.github.model.commit.GitHubRetrieveCommitsResultResponse
import com.test.task.platform.commons.RemoteResultParams
import org.assertj.core.api.Assertions.assertThat
import org.easymock.EasyMock.expect
import org.junit.Test

class RemoteRepositoryRetrieveContributorsServiceImplUnitTest : AbstractRemoteRepositoryServiceImplUnitTest() {

    @Test
    fun `test with invalid argument`() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { remoteRepositoryService.retrieveContributors(null) }
        assertIllegalArgumentException { remoteRepositoryService.retrieveContributors(emptyString()) }
        verifyAll()
    }

    @Test
    fun `test when rate limit exceeded`() {
        val fullName = uuId()
        val resultResponse = externalClientRestTestHelper.buildGitHubContributorsStatisticsResultResponse()
        resetAll()
        expect(gitHubRepositoriesClient.retrieveContributors(fullName)).andReturn(resultResponse)
        replayAll()
        remoteRepositoryService.retrieveContributors(fullName)
                .apply { assertThat(this).isEqualTo(RemoteResultParams.ofSuccess(resultResponse)) }
        remoteRepositoryService.retrieveContributors(fullName)
                .apply { assertThat(this).isEqualTo(RemoteResultParams.ofError<GitHubRetrieveCommitsResultResponse>()) }
        verifyAll()
    }
    
    @Test
    fun test() {
        val query = uuId()
        val resultResponse = externalClientRestTestHelper.buildGitHubContributorsStatisticsResultResponse()
        resetAll()
        expect(gitHubRepositoriesClient.retrieveContributors(query)).andReturn(resultResponse)
        replayAll()
        remoteRepositoryService.retrieveContributors(query)
                .apply { assertThat(this).isEqualTo(RemoteResultParams.ofSuccess(resultResponse)) }
        verifyAll()
    }

}