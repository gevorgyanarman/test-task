package com.test.task.service.repository.user.impl

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class RepositoryContributorFindByRepositoryAndContributorServiceIntegrationTest : AbstractRepositoryContributorServiceIntegrationTest() {

    @Test
    fun `test when nothing was found`() {
        val userId = userIntegrationTestHelper.persistUser().id
        val repositoryId = repositoryIntegrationTestHelper.persistRepository().id
        repositoryContributorService.findByRepositoryAndUser(randomLong(), userId)
                .apply { assertThat(this).isEmpty }
        repositoryContributorService.findByRepositoryAndUser(repositoryId, randomLong())
                .apply { assertThat(this).isEmpty }
    }

    @Test
    fun test() {
        val repositoryContributor = integrationTestHelper.persistRepositoryContributor().apply { flushAndClear() }
        repositoryContributorService.findByRepositoryAndUser(repositoryContributor.repository.id, repositoryContributor.user.id)
                .apply { assertThat(this).isPresent }
                .get()
                .apply { assertThat(this).isEqualTo(repositoryContributor) }
    }
}