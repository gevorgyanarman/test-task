package com.test.task.facade.repository.impl

import com.test.task.model.repository.error.RepositoryErrorResponseModel
import org.assertj.core.api.Assertions.assertThat
import org.easymock.EasyMock.expect
import org.junit.Test
import java.util.*


class RepositoryRetrieveUsersFacadeImplUnitTest : AbstractRepositoryFacadeImplUnitTest() {

    @Test
    fun `test when repository not found`() {
        val request = restTestHelper.buildRetrieveRepositoryContributorsRequest()
        resetAll()
        expect(repositoryService.findByUuId(request.repositoryUuId)).andReturn(Optional.empty())
        replayAll()
        repositoryFacade.retrieveContributors(request)
                .apply { assertBasicErrorResultResponse(this, RepositoryErrorResponseModel.REPOSITORY_NOT_FOUND) }
        verifyAll()
    }

    @Test
    fun test() {
        val request = restTestHelper.buildRetrieveRepositoryContributorsRequest()
        val repository = commonTestHelper.buildRepository()
        val users = listOf(userCommonTestHelper.buildUser(), userCommonTestHelper.buildUser())
        val models = listOf(
                userRestTetHelper.buildUserResponseModel(),
                userRestTetHelper.buildUserResponseModel()
        )
        resetAll()
        expect(repositoryService.findByUuId(request.repositoryUuId)).andReturn(Optional.of(repository))
        expect(repositoryServiceFacade.retrieveContributors(repository.id)).andReturn(users)
        repeat(users.size) { expect(userModelBuilder.build(users[it])).andReturn(models[it]) }
        replayAll()
        repositoryFacade.retrieveContributors(request)
                .apply { assertBasicSuccessResultResponse(this) }
                .response()
                .apply { assertThat(this.totalCount()).isEqualTo(users.size.toLong()) }
                .items()
                .apply { assertThat(this).containsExactlyElementsOf(models) }
        verifyAll()
    }
}