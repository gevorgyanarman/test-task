package com.test.task.service.repository.user.impl

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class RepositoryContributorFindByRepositoryServiceIntegrationTest : AbstractRepositoryContributorServiceIntegrationTest() {

    @Test
    fun `test when nothing was found`() {
        repositoryIntegrationTestHelper.persistRepository()
        repositoryContributorService.findByRepository(randomLong())
                .apply { assertThat(this).isEmpty() }
    }

    @Test
    fun test() {
        val repository = repositoryIntegrationTestHelper.persistRepository()
        val repositoryContributors1 = integrationTestHelper.persistRepositoryContributor(repositoryId = repository.id)
        val repositoryContributors2 = integrationTestHelper.persistRepositoryContributor(repositoryId = repository.id)
        flushAndClear()
        repositoryContributorService.findByRepository(repository.id)
                .apply { assertThat(this).isNotEmpty }
                .apply { assertThat(this.size).isEqualTo(2) }
                .apply { assertThat(this).containsExactlyInAnyOrder(repositoryContributors1, repositoryContributors2) }
    }
}