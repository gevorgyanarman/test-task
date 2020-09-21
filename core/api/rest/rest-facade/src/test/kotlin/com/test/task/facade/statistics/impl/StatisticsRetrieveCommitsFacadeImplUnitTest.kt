package com.test.task.facade.statistics.impl

import com.test.task.model.statistics.error.StatisticsErrorResponseModel
import org.assertj.core.api.Assertions.assertThat
import org.easymock.EasyMock.expect
import org.junit.Test
import java.util.*


class StatisticsRetrieveCommitsFacadeImplUnitTest : AbstractStatisticsFacadeImplUnitTest() {

    @Test
    fun `test when repository not found`() {
        val request = statisticsRestTestHelper.buildRetrieveCommitsStatisticsRequest()
        resetAll()
        expect(repositoryService.findByUuId(request.repositoryUuId)).andReturn(Optional.empty())
        replayAll()
        statisticsFacade.retrieveCommits(request)
                .apply { assertBasicErrorResultResponse(this, StatisticsErrorResponseModel.REPOSITORY_NOT_FOUND) }
        verifyAll()
    }

    @Test
    fun test() {
        val request = statisticsRestTestHelper.buildRetrieveCommitsStatisticsRequest()
        val repository = repositoryCommonTestHelper.buildRepository()
        val user1 = userCommonTestHelper.buildUser()
        val user2 = userCommonTestHelper.buildUser()
        val commits = listOf(
                commitCommonTestHelper.buildCommit(user = user1),
                commitCommonTestHelper.buildCommit(user = user1),
                commitCommonTestHelper.buildCommit(user = user2)
        )
        val user1ResponseModel = userRestTetHelper.buildUserResponseModel()
        val user2ResponseModel = userRestTetHelper.buildUserResponseModel()
        val statisticsItemsModels = listOf(
                statisticsRestTestHelper.buildCommitsStatisticsItemResponseModel(user = user1ResponseModel, commitsCount = 2L),
                statisticsRestTestHelper.buildCommitsStatisticsItemResponseModel(user = user2ResponseModel, commitsCount = 1L)
        )
        resetAll()
        expect(repositoryService.findByUuId(request.repositoryUuId)).andReturn(Optional.of(repository))
        expect(commitServiceFacade.retrieve(repository.id)).andReturn(commits)
        expect(userModelBuilder.build(user1)).andReturn(user1ResponseModel)
        expect(userModelBuilder.build(user2)).andReturn(user2ResponseModel)
        replayAll()
        statisticsFacade.retrieveCommits(request)
                .apply { assertBasicSuccessResultResponse(this) }
                .response()
                .apply { assertThat(this.statisticsCommitsCount).isEqualTo(commits.size) }
                .items
                .apply { assertThat(this).containsExactlyInAnyOrderElementsOf(statisticsItemsModels) }
        verifyAll()
    }

}