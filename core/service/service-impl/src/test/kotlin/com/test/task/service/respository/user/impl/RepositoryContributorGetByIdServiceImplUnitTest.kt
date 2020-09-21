package com.test.task.service.respository.user.impl

import com.test.task.service.commons.EntityNotFoundForIdException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.easymock.EasyMock.expect
import org.junit.Test
import java.util.*

class RepositoryContributorGetByIdServiceImplUnitTest : AbstractRepositoryContributorServiceImplUnitTest() {

    @Test
    fun `test with invalid argument`() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { repositoryContributorService.getById(null) }
        verifyAll()
    }

    @Test
    fun `test when not found`() {
        val id = randomLong()
        resetAll()
        expect(repositoryContributorRepository.findById(id)).andReturn(Optional.empty())
        replayAll()
        assertThatThrownBy { repositoryContributorService.getById(id) }
                .isExactlyInstanceOf(EntityNotFoundForIdException::class.java)
        verifyAll()
    }

    @Test
    fun test() {
        val id = randomLong()
        val repositoryContributor = commonTestHelper.buildRepositoryContributor().apply { setId(id) }
        resetAll()
        expect(repositoryContributorRepository.findById(id)).andReturn(Optional.of(repositoryContributor))
        replayAll()
        repositoryContributorService.getById(id)
                .apply { assertThat(this).isEqualTo(repositoryContributor) }
        verifyAll()
    }
}