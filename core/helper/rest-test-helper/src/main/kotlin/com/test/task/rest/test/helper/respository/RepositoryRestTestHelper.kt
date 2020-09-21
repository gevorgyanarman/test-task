package com.test.task.rest.test.helper.respository

import com.test.task.model.repository.response.RepositoryItemResponseModel
import com.test.task.model.repository.request.RepositorySearchRequest
import com.test.task.model.repository.request.RetrieveRepositoryContributorsRequest
import com.test.task.rest.test.helper.AbstractRestTestHelper


class RepositoryRestTestHelper : AbstractRestTestHelper() {

    fun buildRepositorySearchRequest(query: String? = uuId()
    ): RepositorySearchRequest = RepositorySearchRequest(query)

    fun buildRepositoryItemResponseModel(uuId: String? = uuId(),
                                         name: String? = uuId(),
                                         fullName: String? = uuId()
    ): RepositoryItemResponseModel = RepositoryItemResponseModel(uuId, name, fullName)

    fun buildRetrieveRepositoryContributorsRequest(repositoryUuId: String? = uuId()
    ): RetrieveRepositoryContributorsRequest = RetrieveRepositoryContributorsRequest(repositoryUuId)

}