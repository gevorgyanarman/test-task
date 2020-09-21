package com.test.task.helper.unit.repository.user

import com.test.task.domain.respository.Repository
import com.test.task.domain.respository.RepositoryContributor
import com.test.task.domain.user.User
import com.test.task.helper.AbstractTestHelper
import com.test.task.helper.unit.repository.RepositoryCommonTestHelper
import com.test.task.helper.unit.user.UserCommonTestHelper
import com.test.task.service.repository.user.dto.CreateRepositoryContributorDto


open class RepositoryContributorCommonTestHelper : AbstractTestHelper() {

    private val repositoryCommonTestHelper = RepositoryCommonTestHelper()
    private val userCommonTestHelper = UserCommonTestHelper()

    fun buildCreateRepositoryContributorDto(userId: Long? = randomLong(),
                                            repositoryId: Long = randomLong()
    ): CreateRepositoryContributorDto = CreateRepositoryContributorDto(userId, repositoryId)

    fun buildRepositoryContributor(repository: Repository = repositoryCommonTestHelper.buildRepository(),
                                   user: User = userCommonTestHelper.buildUser()
    ): RepositoryContributor = RepositoryContributor(repository, user)
}