package com.test.task.service.respository.impl

import org.assertj.core.api.Assertions.assertThat
import org.easymock.EasyMock.expect
import org.junit.Test
import java.util.*


class RepositoryFindByRemoteIdServiceImplUnitTest : AbstractRepositoryServiceImplUnitTest() {

    @Test
    fun `test with invalid argument`() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { repositoryService.findByRemoteId(null) }
        assertIllegalArgumentException { repositoryService.findByRemoteId(emptyString()) }
        verifyAll()
    }

    @Test
    fun `test when not found`() {
        val remoteId = uuId()
        resetAll()
        expect(repositoryRepository.findByRemoteId(remoteId)).andReturn(Optional.empty())
        replayAll()
        repositoryService.findByRemoteId(remoteId)
                .apply { assertThat(this).isEmpty }
        verifyAll()
    }

    @Test
    fun test() {
        val remoteId = uuId()
        val repository = commonTestHelper.buildRepository()
        resetAll()
        expect(repositoryRepository.findByRemoteId(remoteId)).andReturn(Optional.of(repository))
        replayAll()
        repositoryService.findByRemoteId(remoteId)
                .apply { assertThat(this).isPresent }
                .get()
                .apply { assertThat(this).isEqualTo(repository) }
        verifyAll()
    }
}