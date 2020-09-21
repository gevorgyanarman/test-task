package com.test.task.service.commit.impl

import com.test.task.domain.respository.commit.Commit
import org.assertj.core.api.Assertions.assertThat
import org.easymock.EasyMock.*
import org.junit.Test

class CommitCreateServiceImplUnitTest : AbstractCommitServiceImplUnitTest() {

    @Test
    fun `test with invalid argument`() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { commitService.create(null) }
        verifyAll()
    }

    @Test
    fun test() {
        val user = userCommonTestHelper.buildUser()
        val repository = repositoryCommonTestHelper.buildRepository()
        val dto = commonTestHelper.buildCreateCommitDto()
        resetAll()
        expect(userService.getById(dto.userId)).andReturn(user)
        expect(repositoryService.getById(dto.repositoryId)).andReturn(repository)
        expect(commitRepository.save(isA(Commit::class.java))).andAnswer { getCurrentArguments()[0] as Commit }
        replayAll()
        commitService.create(dto)
                .apply { assertThat(this.user).isEqualTo(user) }
                .apply { assertThat(this.repository).isEqualTo(repository) }
                .apply { assertThat(this.sha).isEqualTo(dto.sha) }
                .apply { assertThat(this.remoteCreationDate).isEqualTo(dto.remoteCreationDate) }
        verifyAll()
    }
}