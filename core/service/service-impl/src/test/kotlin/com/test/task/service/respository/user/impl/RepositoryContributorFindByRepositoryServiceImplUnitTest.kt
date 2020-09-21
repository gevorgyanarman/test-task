package com.test.task.service.respository.user.impl

import org.assertj.core.api.Assertions.assertThat
import org.easymock.EasyMock.expect
import org.junit.Test

class RepositoryContributorFindByRepositoryServiceImplUnitTest : AbstractRepositoryContributorServiceImplUnitTest() {

    @Test
    fun `test with invalid argument`() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { repositoryContributorService.findByRepository(null) }
        verifyAll()
    }

    @Test
    fun test() {
        val repositoryId = randomLong()
        val repositoryUContributors = listOf(
                commonTestHelper.buildRepositoryContributor(),
                commonTestHelper.buildRepositoryContributor()
        )
        resetAll()
        expect(repositoryContributorRepository.findByRepositoryId(repositoryId)).andReturn(repositoryUContributors)
        replayAll()
        repositoryContributorService.findByRepository(repositoryId)
                .apply { assertThat(this).containsOnlyElementsOf(repositoryUContributors) }
        verifyAll()
    }
}