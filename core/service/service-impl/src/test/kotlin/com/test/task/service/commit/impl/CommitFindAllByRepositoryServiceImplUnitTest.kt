package com.test.task.service.commit.impl

import org.assertj.core.api.Assertions.assertThat
import org.easymock.EasyMock.expect
import org.junit.Test

class CommitFindAllByRepositoryServiceImplUnitTest : AbstractCommitServiceImplUnitTest() {

    @Test
    fun `test with illegal argument`() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { commitService.findAllByRepository(null) }
        verifyAll()
    }

    @Test
    fun test() {
        val repositoryId = randomLong()
        val commits = listOf(commonTestHelper.buildCommit(), commonTestHelper.buildCommit())
        resetAll()
        expect(commitRepository.findAllByRepositoryId(repositoryId)).andReturn(commits)
        replayAll()
        commitService.findAllByRepository(repositoryId)
                .apply { assertThat(this).containsExactlyElementsOf(commits) }
        verifyAll()
    }
}