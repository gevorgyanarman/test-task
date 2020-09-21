package com.test.task.service.respository.impl

import org.assertj.core.api.Assertions.assertThat
import org.easymock.EasyMock.expect
import org.junit.Test
import java.util.*

class RepositoryFindByUuIdServiceImplUnitTest : AbstractRepositoryServiceImplUnitTest() {

    @Test
    fun `test with invalid argument`() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { repositoryService.findByUuId(null) }
        assertIllegalArgumentException { repositoryService.findByUuId(emptyString()) }
        verifyAll()
    }

    @Test
    fun `test when not found`() {
        val uuId = uuId()
        resetAll()
        expect(repositoryRepository.findByUuId(uuId)).andReturn(Optional.empty())
        replayAll()
        repositoryService.findByUuId(uuId)
                .apply { assertThat(this).isEmpty }
        verifyAll()
    }

    @Test
    fun test() {
        val repository = commonTestHelper.buildRepository()
        val uuId = repository.uuId
        resetAll()
        expect(repositoryRepository.findByUuId(uuId)).andReturn(Optional.of(repository))
        replayAll()
        repositoryService.findByUuId(uuId)
                .apply { assertThat(this).isPresent }
                .get()
                .apply { assertThat(this).isEqualTo(repository) }
        verifyAll()
    }
}