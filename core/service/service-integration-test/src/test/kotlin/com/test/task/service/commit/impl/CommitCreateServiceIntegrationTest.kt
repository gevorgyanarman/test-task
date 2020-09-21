package com.test.task.service.commit.impl

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test


class CommitCreateServiceIntegrationTest : AbstractCommitServiceIntegrationTest() {

    @Test
    fun test() {
        val user = userIntegrationTestHelper.persistUser()
        val repository = repositoryIntegrationTestHelper.persistRepository()
        val dto = integrationTestHelper.buildCreateCommitDto(
                userId = user.id,
                repositoryId = repository.id
        )
        commitService.create(dto)
                .apply { flushAndClear() }
                .apply { assertThat(this.remoteCreationDate).isEqualTo(dto.remoteCreationDate) }
                .apply { assertThat(this.sha).isEqualTo(dto.sha) }
                .apply { assertThat(this.user).isEqualTo(user) }
                .apply { assertThat(this.repository).isEqualTo(repository) }
    }

    @Test
    fun `test multiple creation with same sha`() {
        val user = userIntegrationTestHelper.persistUser()
        val repository = repositoryIntegrationTestHelper.persistRepository()
        val dto = integrationTestHelper.buildCreateCommitDto(
                userId = user.id,
                repositoryId = repository.id
        )
        commitService.create(dto).apply { flushAndClear() }
        assertThatThrownBy { commitService.create(dto).apply { flushAndClear() } }
    }
}