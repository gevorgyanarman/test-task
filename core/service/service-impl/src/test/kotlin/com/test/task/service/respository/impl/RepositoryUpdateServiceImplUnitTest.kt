package com.test.task.service.respository.impl

import com.test.task.domain.respository.Repository
import org.assertj.core.api.Assertions
import org.easymock.EasyMock
import org.easymock.EasyMock.expect
import org.easymock.EasyMock.getCurrentArguments
import org.junit.Test
import java.util.*

class RepositoryUpdateServiceImplUnitTest : AbstractRepositoryServiceImplUnitTest() {

    @Test
    fun `test with illegal argument`() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { repositoryService.update(null) }
        verifyAll()
    }

    @Test
    fun test() {
        val dto = commonTestHelper.buildUpdateRepositoryDto()
        val user = commonTestHelper.buildRepository().apply { this.id = dto.id }
        resetAll()
        expect(repositoryRepository.findById(dto.id)).andReturn(Optional.of(user))
        expect(repositoryRepository.save(EasyMock.isA(Repository::class.java))).andAnswer { getCurrentArguments()[0] as Repository }
        replayAll()
        repositoryService.update(dto)
                .apply { Assertions.assertThat(this.id).isEqualTo(id) }
                .apply { Assertions.assertThat(this.remoteId).isEqualTo(user.remoteId) }
                .apply { Assertions.assertThat(this.name).isEqualTo(dto.name) }
                .apply { Assertions.assertThat(this.fullName).isEqualTo(dto.fullName) }
        verifyAll()
    }
}