package com.test.task.service.respository.impl

import com.test.task.domain.respository.Repository
import org.assertj.core.api.Assertions.assertThat
import org.easymock.EasyMock.*
import org.junit.Test


class RepositoryCreateServiceImplUnitTest : AbstractRepositoryServiceImplUnitTest() {

    @Test
    fun `test with invalid argument`() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { repositoryService.create(null) }
        verifyAll()
    }

    @Test
    fun test() {
        resetAll()
        val dto = commonTestHelper.buildCreateRepositoryDto()
        expect(repositoryRepository.save(isA(Repository::class.java))).andAnswer { getCurrentArguments()[0] as Repository }
        replayAll()
        repositoryService.create(dto)
                .apply { assertThat(this.remoteId).isEqualTo(dto.remoteId) }
                .apply { assertThat(this.name).isEqualTo(dto.name) }
                .apply { assertThat(this.fullName).isEqualTo(dto.fullName) }
        verifyAll()
    }
}