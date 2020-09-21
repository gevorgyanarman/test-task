package com.test.task.facade.repository.impl

import com.test.task.platform.repository.params.RepositoriesSearchResultParams
import org.assertj.core.api.Assertions.assertThat
import org.easymock.EasyMock.expect
import org.junit.Test


class RepositorySearchFacadeImplUnitTest : AbstractRepositoryFacadeImplUnitTest() {

    @Test
    fun `test when empty result is returned`() {
        val request = restTestHelper.buildRepositorySearchRequest()
        resetAll()
        expect(repositoryServiceFacade.search(request.query)).andReturn(RepositoriesSearchResultParams.empty())
        replayAll()
        repositoryFacade.search(request)
                .apply { assertBasicSuccessResultResponse(this) }
        verifyAll()
    }

    @Test
    fun test() {
        val request = restTestHelper.buildRepositorySearchRequest()
        val totalCount = 100L
        val repositories = listOf(commonTestHelper.buildRepository(), commonTestHelper.buildRepository())
        val itemModels = listOf(
                restTestHelper.buildRepositoryItemResponseModel(),
                restTestHelper.buildRepositoryItemResponseModel()
        )
        resetAll()
        expect(repositoryServiceFacade.search(request.query)).andReturn(RepositoriesSearchResultParams.of(totalCount, repositories))
        repeat(repositories.size) {
            expect(repositoryItemModelBuilder.build(repositories[it])).andReturn(itemModels[it])
        }
        replayAll()
        repositoryFacade.search(request)
                .apply { assertBasicSuccessResultResponse(this) }
                .response()
                .apply { assertThat(this.totalCount()).isEqualTo(totalCount) }
                .items()
                .apply { assertThat(this).containsExactlyElementsOf(itemModels) }
        verifyAll()
    }
}