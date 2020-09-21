package com.test.task.service.repository.impl

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class RepositoryFindByIdServiceIntegrationTest : AbstractRepositoryServiceIntegrationTest() {

    @Test
    fun `test when not found`() {
        repositoryService.findById(randomLong())
                .apply { assertThat(this).isNotPresent }
    }

    @Test
    fun `test when found`() {
        val repository = integrationTestHelper.persistRepository()
        val repositoryId = repository.apply { flushAndClear() }.id
        repositoryService.findById(repositoryId)
                .apply { assertThat(this).isPresent }.get()
                .apply { assertThat(this).isEqualTo(repository) }
    }
}