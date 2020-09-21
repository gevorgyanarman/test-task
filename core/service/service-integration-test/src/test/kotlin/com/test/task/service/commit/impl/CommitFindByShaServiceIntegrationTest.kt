package com.test.task.service.commit.impl

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class CommitFindByShaServiceIntegrationTest : AbstractCommitServiceIntegrationTest() {

    @Test
    fun `test when not found`() {
        commitService.findBySha(uuId())
                .apply { assertThat(this).isEmpty }
    }

    @Test
    fun test() {
        val sha = uuId()
        val commit = integrationTestHelper.persistCommit(sha = sha).apply { flushAndClear() }
        commitService.findBySha(sha)
                .apply { assertThat(this).isPresent }
                .get()
                .apply { assertThat(this).isEqualTo(commit) }
    }
}