package com.test.task.service.commit.impl

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class CommitGetByIdServiceIntegrationTest : AbstractCommitServiceIntegrationTest() {

    @Test
    fun test() {
        val commit = integrationTestHelper.persistCommit().apply { flushAndClear() }
        commitService.getById(commit.id)
                .apply { assertThat(this).isEqualTo(commit) }
    }
}