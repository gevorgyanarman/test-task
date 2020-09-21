package com.test.task.service.commit.impl

import org.assertj.core.api.Assertions.assertThat
import org.easymock.EasyMock.expect
import org.junit.Test
import java.util.*

class CommitFindByShaServiceImplUnitTest : AbstractCommitServiceImplUnitTest() {

    @Test
    fun `test with invalid arguments`() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { commitService.findBySha(null) }
        assertIllegalArgumentException { commitService.findBySha(emptyString()) }
        verifyAll()
    }

    @Test
    fun `test when not found`() {
        val sha = uuId()
        resetAll()
        expect(commitRepository.findBySha(sha)).andReturn(Optional.empty())
        replayAll()
        commitService.findBySha(sha)
                .apply { assertThat(this).isEmpty }
        verifyAll()
    }

    @Test
    fun test() {
        val commit = commonTestHelper.buildCommit()
        val sha = commit.sha
        resetAll()
        expect(commitRepository.findBySha(sha)).andReturn(Optional.of(commit))
        replayAll()
        commitService.findBySha(sha)
                .apply { assertThat(this).isPresent }
                .get()
                .apply { assertThat(this).isEqualTo(commit) }
        verifyAll()
    }
}