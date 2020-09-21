package com.test.task.platform.repository.impl

import com.test.task.github.model.commit.GitHubRetrieveCommitsResultResponse
import com.test.task.platform.commons.RemoteResultParams
import org.assertj.core.api.Assertions.assertThat
import org.easymock.EasyMock
import org.easymock.EasyMock.expect
import org.junit.Test


class RemoteRepositorySearchServiceImplUnitTest : AbstractRemoteRepositoryServiceImplUnitTest() {

    @Test
    fun `test with invalid argument`() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { remoteRepositoryService.search(null) }
        assertIllegalArgumentException { remoteRepositoryService.search(emptyString()) }
        verifyAll()
    }

    @Test
    fun `test when rate limit exceeded`() {
        val query = uuId()
        val resultResponse = externalClientRestTestHelper.buildGitHubRepositorySearchResultResponse()
        resetAll()
        expect(gitHubSearchClient.search(query)).andReturn(resultResponse)
        replayAll()
        remoteRepositoryService.search(query)
                .apply { assertThat(this).isEqualTo(RemoteResultParams.ofSuccess(resultResponse)) }
        remoteRepositoryService.search(query)
                .apply { assertThat(this).isEqualTo(RemoteResultParams.ofError<GitHubRetrieveCommitsResultResponse>()) }
        verifyAll()
    }

    @Test
    fun test() {
        val query = uuId()
        val resultResponse = externalClientRestTestHelper.buildGitHubRepositorySearchResultResponse()
        resetAll()
        expect(gitHubSearchClient.search(query)).andReturn(resultResponse)
        replayAll()
        remoteRepositoryService.search(query)
                .apply { assertThat(this).isEqualTo(RemoteResultParams.ofSuccess(resultResponse)) }
        verifyAll()
    }

}