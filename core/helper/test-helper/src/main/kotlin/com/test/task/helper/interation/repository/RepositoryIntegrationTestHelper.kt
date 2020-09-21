package com.test.task.helper.interation.repository

import com.test.task.domain.respository.Repository
import com.test.task.helper.unit.repository.RepositoryCommonTestHelper
import com.test.task.service.repository.RepositoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class RepositoryIntegrationTestHelper @Autowired constructor(
        private val repositoryService: RepositoryService
) : RepositoryCommonTestHelper() {

    fun persistRepository(
            remoteId: String? = uuId(),
            name: String? = uuId(),
            fullName: String? = uuId()
    ): Repository = repositoryService.create(buildCreateRepositoryDto(remoteId, name, fullName))
}