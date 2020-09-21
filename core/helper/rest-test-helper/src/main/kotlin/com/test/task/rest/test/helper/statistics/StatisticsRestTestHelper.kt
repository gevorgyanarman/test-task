package com.test.task.rest.test.helper.statistics

import com.test.task.model.repository.response.UserResponseModel
import com.test.task.model.statistics.response.CommitsStatisticsItemResponseModel
import com.test.task.model.statistics.request.RetrieveCommitsStatisticsRequest
import com.test.task.rest.test.helper.AbstractRestTestHelper
import com.test.task.rest.test.helper.user.UserRestTetHelper

class StatisticsRestTestHelper : AbstractRestTestHelper() {

    private val userRestTestHelper = UserRestTetHelper()

    fun buildRetrieveCommitsStatisticsRequest(
            repositoryUuId: String? = uuId()
    ): RetrieveCommitsStatisticsRequest = RetrieveCommitsStatisticsRequest(repositoryUuId)

    fun buildCommitsStatisticsItemResponseModel(user: UserResponseModel? = userRestTestHelper.buildUserResponseModel(),
                                                commitsCount: Long? = randomLong()
    ): CommitsStatisticsItemResponseModel = CommitsStatisticsItemResponseModel(user, commitsCount)
}