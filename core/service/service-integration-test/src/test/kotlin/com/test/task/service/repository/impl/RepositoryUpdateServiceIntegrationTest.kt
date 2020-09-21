package com.test.task.service.repository.impl

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class RepositoryUpdateServiceIntegrationTest : AbstractRepositoryServiceIntegrationTest() {

    @Test
    fun test() {
        val repository = integrationTestHelper.persistRepository()
        flushAndClear()
        val dto = integrationTestHelper.buildUpdateRepositoryDto(id = repository.id)
        repositoryService.update(dto).apply { flushAndClear() }
        repositoryService.getById(repository.id)
                .apply { assertThat(this.name).isEqualTo(dto.name) }
                .apply { assertThat(this.fullName).isEqualTo(dto.fullName) }
                .apply { assertThat(this.remoteId).isEqualTo(repository.remoteId) }
    }
}