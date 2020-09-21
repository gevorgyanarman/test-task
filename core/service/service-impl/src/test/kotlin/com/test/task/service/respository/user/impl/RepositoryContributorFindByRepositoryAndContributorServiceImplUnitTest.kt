package com.test.task.service.respository.user.impl

import org.assertj.core.api.Assertions.assertThat
import org.easymock.EasyMock.expect
import org.junit.Test
import java.util.*


class RepositoryContributorFindByRepositoryAndContributorServiceImplUnitTest : AbstractRepositoryContributorServiceImplUnitTest() {

    @Test
    fun `test with invalid arguments`() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { repositoryContributorService.findByRepositoryAndUser(null, randomLong()) }
        assertIllegalArgumentException { repositoryContributorService.findByRepositoryAndUser(randomLong(), null) }
        verifyAll()
    }

    @Test
    fun `test when not found`() {
        val repositoryId = randomLong()
        val userId = randomLong()
        resetAll()
        expect(repositoryContributorRepository.findByRepositoryIdAndUserId(repositoryId, userId)).andReturn(Optional.empty())
        replayAll()
        repositoryContributorService.findByRepositoryAndUser(repositoryId, userId)
                .apply { assertThat(this).isEmpty }
        verifyAll()
    }

    @Test
    fun test() {
        val repositoryId = randomLong()
        val userId = randomLong()
        val repositoryContributor = commonTestHelper.buildRepositoryContributor()
        resetAll()
        expect(repositoryContributorRepository.findByRepositoryIdAndUserId(repositoryId, userId)).andReturn(Optional.of(repositoryContributor))
        replayAll()
        repositoryContributorService.findByRepositoryAndUser(repositoryId, userId)
                .apply { assertThat(this).isPresent }
                .get()
                .apply { assertThat(this).isEqualTo(repositoryContributor) }
        verifyAll()
    }
}