package com.test.task.service.commit.impl

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class CommitFindAllByRepositoryServiceIntegrationTest : AbstractCommitServiceIntegrationTest() {

    @Test
    fun `test when not found`() {
        commitService.findAllByRepository(randomLong())
                .apply { assertThat(this).isEmpty() }
    }

    @Test
    fun test() {
        val repository = repositoryIntegrationTestHelper.persistRepository()
        val commit1 = integrationTestHelper.persistCommit(repositoryId = repository.id)
        val commit2 = integrationTestHelper.persistCommit(repositoryId = repository.id)
        flushAndClear()
        commitService.findAllByRepository(repository.id)
                .apply { assertThat(this.size).isEqualTo(2) }
                .apply { assertThat(this).containsExactlyInAnyOrder(commit1, commit2) }
    }
}