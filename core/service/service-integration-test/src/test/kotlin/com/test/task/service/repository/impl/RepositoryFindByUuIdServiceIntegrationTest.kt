package com.test.task.service.repository.impl

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class RepositoryFindByUuIdServiceIntegrationTest : AbstractRepositoryServiceIntegrationTest() {

    @Test
    fun `test when not found`() {
        repositoryService.findByUuId(uuId())
                .apply { assertThat(this).isNotPresent }
    }

    @Test
    fun test() {
        val repository = integrationTestHelper.persistRepository().apply { flushAndClear() }
        repositoryService.findByUuId(repository.uuId)
                .apply { assertThat(this).isPresent }.get()
                .apply { assertThat(this).isEqualTo(repository) }
    }
}