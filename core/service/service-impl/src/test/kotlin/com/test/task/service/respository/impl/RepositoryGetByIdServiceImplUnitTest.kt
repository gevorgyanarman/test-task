package com.test.task.service.respository.impl

import com.test.task.service.commons.EntityNotFoundForIdException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.easymock.EasyMock.expect
import org.junit.Test
import java.util.*

class RepositoryGetByIdServiceImplUnitTest : AbstractRepositoryServiceImplUnitTest() {

    @Test
    fun `test with invalid argument`() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { repositoryService.getById(null) }
        verifyAll()
    }

    @Test
    fun `test when not found`() {
        val id = randomLong()
        resetAll()
        expect(repositoryService.findById(id)).andReturn(Optional.empty())
        replayAll()
        assertThatThrownBy { repositoryService.getById(id) }
                .isExactlyInstanceOf(EntityNotFoundForIdException::class.java)
        verifyAll()
    }

    @Test
    fun test() {
        val id = randomLong()
        val repository = commonTestHelper.buildRepository().apply { setId(id) }
        resetAll()
        expect(repositoryRepository.findById(id)).andReturn(Optional.of(repository))
        replayAll()
        repositoryService.getById(id)
                .apply { assertThat(this).isEqualTo(repository) }
        verifyAll()
    }
}