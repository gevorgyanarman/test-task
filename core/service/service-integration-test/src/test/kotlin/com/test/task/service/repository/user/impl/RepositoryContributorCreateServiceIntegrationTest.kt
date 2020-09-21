package com.test.task.service.repository.user.impl

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test


class RepositoryContributorCreateServiceIntegrationTest : AbstractRepositoryContributorServiceIntegrationTest() {

    @Test
    fun test() {
        val user = userIntegrationTestHelper.persistUser()
        val repository = repositoryIntegrationTestHelper.persistRepository()
        val dto = integrationTestHelper.buildCreateRepositoryContributorDto(
                userId = user.id,
                repositoryId = repository.id
        )
        repositoryContributorService.create(dto)
                .apply { flushAndClear() }
                .apply { assertThat(this.user).isEqualTo(user) }
                .apply { assertThat(this.repository).isEqualTo(repository) }
    }

    @Test
    fun `test multiple creation`() {
        val user = userIntegrationTestHelper.persistUser()
        val repository = repositoryIntegrationTestHelper.persistRepository()
        val dto = integrationTestHelper.buildCreateRepositoryContributorDto(
                userId = user.id,
                repositoryId = repository.id
        )
        repositoryContributorService.create(dto).apply { flushAndClear() }
        assertThatThrownBy { repositoryContributorService.create(dto).apply { flushAndClear() } }
    }
}