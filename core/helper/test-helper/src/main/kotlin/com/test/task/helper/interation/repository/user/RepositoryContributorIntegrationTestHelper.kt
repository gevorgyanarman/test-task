package com.test.task.helper.interation.repository.user

import com.test.task.domain.respository.RepositoryContributor
import com.test.task.helper.interation.repository.RepositoryIntegrationTestHelper
import com.test.task.helper.interation.user.UserIntegrationTestHelper
import com.test.task.helper.unit.repository.user.RepositoryContributorCommonTestHelper
import com.test.task.service.repository.user.RepositoryContributorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class RepositoryContributorIntegrationTestHelper @Autowired constructor(
        private val repositoryContributorService: RepositoryContributorService,
        private val userIntegrationTestHelper: UserIntegrationTestHelper,
        private val repositoryIntegrationTestHelper: RepositoryIntegrationTestHelper
) : RepositoryContributorCommonTestHelper() {

    fun persistRepositoryContributor(
            userId: Long = userIntegrationTestHelper.persistUser().id,
            repositoryId: Long = repositoryIntegrationTestHelper.persistRepository().id
    ): RepositoryContributor = buildCreateRepositoryContributorDto(
            userId = userId,
            repositoryId = repositoryId
    ).let { repositoryContributorService.create(it) }
}