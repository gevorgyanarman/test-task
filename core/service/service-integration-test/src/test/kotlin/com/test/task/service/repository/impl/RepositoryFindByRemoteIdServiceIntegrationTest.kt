package com.test.task.service.repository.impl

import org.assertj.core.api.Assertions
import org.junit.Test

class RepositoryFindByRemoteIdServiceIntegrationTest : AbstractRepositoryServiceIntegrationTest() {

    @Test
    fun `test when not found`() {
        repositoryService.findByRemoteId(uuId())
                .apply { Assertions.assertThat(this).isNotPresent }
    }

    @Test
    fun test() {
        val repository = integrationTestHelper.persistRepository().apply { flushAndClear() }
        repositoryService.findByRemoteId(repository.remoteId)
                .apply { Assertions.assertThat(this).isPresent }.get()
                .apply { Assertions.assertThat(this).isEqualTo(repository) }
    }
}