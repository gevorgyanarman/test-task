package com.test.task.service.respository.impl

import org.assertj.core.api.Assertions.assertThat
import org.easymock.EasyMock.expect
import org.junit.Test
import java.util.*


class RepositoryFindByIdServiceImplUnitTest : AbstractRepositoryServiceImplUnitTest() {

    @Test
    fun `test with invalid argument`() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { repositoryService.findById(null) }
        verifyAll()
    }

    @Test
    fun `test when not found`() {
        val id = randomLong()
        resetAll()
        expect(repositoryRepository.findById(id)).andReturn(Optional.empty())
        replayAll()
        repositoryService.findById(id)
                .apply { assertThat(this).isEmpty }
        verifyAll()
    }

    @Test
    fun test() {
        val id = randomLong()
        val repository = commonTestHelper.buildRepository().apply { this.id = id }
        resetAll()
        expect(repositoryRepository.findById(id)).andReturn(Optional.of(repository))
        replayAll()
        repositoryService.findById(id)
                .apply { assertThat(this).isPresent }
                .get()
                .apply { assertThat(this).isEqualTo(repository) }
        verifyAll()
    }
}