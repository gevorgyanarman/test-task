package com.test.task.service.respository.user.impl

import com.test.task.domain.respository.RepositoryContributor
import org.assertj.core.api.Assertions.assertThat
import org.easymock.EasyMock.*
import org.junit.Test

class RepositoryContributorCreateServiceImplUnitTest : AbstractRepositoryContributorServiceImplUnitTest() {

    @Test
    fun `test with invalid arguments`() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { repositoryContributorService.create(null) }
        verifyAll()
    }

    @Test
    fun test() {
        resetAll()
        val user = userCommonTestHelper.buildUser()
        val repository = repositoryCommonTestHelper.buildRepository()
        val dto = commonTestHelper.buildCreateRepositoryContributorDto()
        expect(userService.getById(dto.userId)).andReturn(user)
        expect(repositoryService.getById(dto.repositoryId)).andReturn(repository)
        expect(repositoryContributorRepository.save(isA(RepositoryContributor::class.java))).andAnswer { getCurrentArguments()[0] as RepositoryContributor }
        replayAll()
        repositoryContributorService.create(dto)
                .apply { assertThat(this.user).isEqualTo(user) }
                .apply { assertThat(this.repository).isEqualTo(repository) }
        verifyAll()
    }
}