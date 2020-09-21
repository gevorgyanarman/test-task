package com.test.task.service.repository.impl

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test


class RepositoryCreateServiceIntegrationTest : AbstractRepositoryServiceIntegrationTest() {

    @Test
    fun test() {
        val dto = integrationTestHelper.buildCreateRepositoryDto()
        repositoryService.create(dto)
                .apply { flushAndClear() }
                .apply { assertThat(this.remoteId).isEqualTo(dto.remoteId) }
                .apply { assertThat(this.fullName).isEqualTo(dto.fullName) }
                .apply { assertThat(this.name).isEqualTo(dto.name) }
    }

    @Test
    fun `test multiple creation with dame remote id`() {
        val dto = integrationTestHelper.buildCreateRepositoryDto()
        repositoryService.create(dto).apply { flushAndClear() }
        assertThatThrownBy { repositoryService.create(dto).apply { flushAndClear() } }
    }
}