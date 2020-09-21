package com.test.task.service.commit.impl

import com.test.task.service.commons.EntityNotFoundForIdException
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.easymock.EasyMock
import org.easymock.EasyMock.expect
import org.junit.Test
import java.util.*

class CommitGetByIdServiceImplUnitTest : AbstractCommitServiceImplUnitTest() {

    @Test
    fun `test with invalid argument`() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { commitService.getById(null) }
        verifyAll()
    }

    @Test
    fun `test when not found`() {
        val id = randomLong()
        resetAll()
        expect(commitRepository.findById(id)).andReturn(Optional.empty())
        replayAll()
        assertThatThrownBy { commitService.getById(id) }
                .isExactlyInstanceOf(EntityNotFoundForIdException::class.java)
        verifyAll()
    }

    @Test
    fun test() {
        val id = randomLong()
        val commit = commonTestHelper.buildCommit().apply { setId(id) }
        resetAll()
        expect(commitRepository.findById(id)).andReturn(Optional.of(commit))
        replayAll()
        commitService.getById(id)
                .apply { assertThat(this).isEqualTo(commit) }
        verifyAll()
    }
}