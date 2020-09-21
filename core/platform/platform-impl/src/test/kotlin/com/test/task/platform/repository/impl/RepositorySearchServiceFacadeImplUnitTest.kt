package com.test.task.platform.repository.impl

import com.test.task.platform.commons.RemoteResultParams
import org.assertj.core.api.Assertions.assertThat
import org.easymock.EasyMock.expect
import org.junit.Test


class RepositorySearchServiceFacadeImplUnitTest : AbstractRepositoryServiceFacadeImplUnitTest() {

    @Test
    fun `test with invalid argument`() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { repositoryServiceFacade.search(null) }
        assertIllegalArgumentException { repositoryServiceFacade.search(emptyString()) }
        verifyAll()
    }

    @Test
    fun `test when could not fetch from remote`() {
        val query = uuId()
        val searchRepositoryDto = repositoryCommonTestHelper.buildSearchRepositoryDto(query = query)
        val page = repositoryCommonTestHelper.buildPage()
        resetAll()
        expect(remoteRepositoryService.search(query)).andReturn(RemoteResultParams.ofError())
        expect(repositoryService.search(searchRepositoryDto)).andReturn(page)
        replayAll()
        repositoryServiceFacade.search(query)
                .apply { assertThat(this.totalCount()).isEqualTo(page.totalElements) }
                .apply { assertThat(this.items()).containsExactlyElementsOf(page.content) }
        verifyAll()
    }

    @Test
    fun `test when fetched from remote`() {
        val query = uuId()
        val repositoryItemViewModel = externalClientRestTestHelper.buildGitHubRepositoryItemViewModel()
        val resultResponse = externalClientRestTestHelper.buildGitHubRepositorySearchResultResponse(
                items = listOf(repositoryItemViewModel)
        )
        val createOrUpdateRepositoryDto = repositoryCommonTestHelper.buildCreateOrUpdateRepositoryDto(
                remoteId = repositoryItemViewModel.id,
                name = repositoryItemViewModel.name,
                fullName = repositoryItemViewModel.fullName
        )
        val repository = repositoryCommonTestHelper.buildRepository()
        resetAll()
        expect(remoteRepositoryService.search(query)).andReturn(RemoteResultParams.ofSuccess(resultResponse))
        expect(repositoryCompoundActionService.createOrUpdate(createOrUpdateRepositoryDto)).andReturn(repository)
        replayAll()
        repositoryServiceFacade.search(query)
                .apply { assertThat(this.totalCount()).isEqualTo(resultResponse.totalCount) }
                .apply { assertThat(this.items()).containsExactly(repository)}
        verifyAll()
    }
}