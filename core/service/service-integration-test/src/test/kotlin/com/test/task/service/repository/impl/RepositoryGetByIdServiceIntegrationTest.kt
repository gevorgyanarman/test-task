package com.test.task.service.repository.impl

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class RepositoryGetByIdServiceIntegrationTest : AbstractRepositoryServiceIntegrationTest() {

    @Test
    fun test() {
        val repository = integrationTestHelper.persistRepository().apply { flushAndClear() }
        repositoryService.getById(repository.id)
                .apply { assertThat(this).isEqualTo(repository) }
    }
}