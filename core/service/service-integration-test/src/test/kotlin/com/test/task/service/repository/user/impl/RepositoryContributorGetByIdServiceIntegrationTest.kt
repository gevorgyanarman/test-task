package com.test.task.service.repository.user.impl

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class RepositoryContributorGetByIdServiceIntegrationTest : AbstractRepositoryContributorServiceIntegrationTest() {

    @Test
    fun test() {
        val repositoryContributor = integrationTestHelper.persistRepositoryContributor().apply { flushAndClear() }
        repositoryContributorService.getById(repositoryContributor.id)
                .apply { assertThat(this).isEqualTo(repositoryContributor) }
    }
}