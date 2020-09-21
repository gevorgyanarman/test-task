package com.test.task.platform.repository.impl

import com.test.task.platform.commons.RemoteResultParams
import org.assertj.core.api.Assertions.assertThat
import org.easymock.EasyMock.expect
import org.junit.Test
import java.util.*


class RepositoryRetrieveContributorsServiceFacadeImplUnitTest : AbstractRepositoryServiceFacadeImplUnitTest() {

    @Test
    fun `test with invalid argument`() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { repositoryServiceFacade.retrieveContributors(null) }
        verifyAll()
    }

    @Test
    fun `test when not fetched from remote`() {
        val repositoryId = randomLong()
        val repository = repositoryCommonTestHelper.buildRepository().apply { this.id = repositoryId }
        val repositoryContributors = listOf(
                repositoryContributorCommonTestHelper.buildRepositoryContributor(),
                repositoryContributorCommonTestHelper.buildRepositoryContributor()
        )
        resetAll()
        expect(repositoryService.getById(repositoryId)).andReturn(repository)
        expect(remoteRepositoryService.retrieveContributors(repository.fullName)).andReturn(RemoteResultParams.ofError())
        expect(repositoryContributorService.findByRepository(repositoryId)).andReturn(repositoryContributors)
        replayAll()
        repositoryServiceFacade.retrieveContributors(repositoryId)
                .apply { assertThat(this).containsExactlyElementsOf(repositoryContributors.map { it.user }) }
        verifyAll()
    }

    @Test
    fun `test when fetched from remote and repository contributor not found in local storage`() {
        val repositoryId = randomLong()
        val repository = repositoryCommonTestHelper.buildRepository().apply { this.id = repositoryId }
        val itemStatisticsViewModel = externalClientRestTestHelper.buildGitHubContributorsItemStatisticsViewModel()
        val resultResponse = externalClientRestTestHelper.buildGitHubContributorsStatisticsResultResponse(
                items = listOf(itemStatisticsViewModel)
        )
        val createOrUpdateUserDto = userCommonTestHelper.buildCreateOrUpdateUserDto(
                remoteId = itemStatisticsViewModel.user.id,
                login = itemStatisticsViewModel.user.login
        )
        val userId = randomLong()
        val user = userCommonTestHelper.buildUser().apply { this.id = userId }
        val createRepositoryContributorDto = repositoryContributorCommonTestHelper.buildCreateRepositoryContributorDto(
                userId = userId,
                repositoryId = repositoryId
        )
        val repositoryContributor = repositoryContributorCommonTestHelper.buildRepositoryContributor()
        resetAll()
        expect(repositoryService.getById(repositoryId)).andReturn(repository)
        expect(remoteRepositoryService.retrieveContributors(repository.fullName)).andReturn(RemoteResultParams.ofSuccess(resultResponse))
        expect(userCompoundActionService.createOrUpdate(createOrUpdateUserDto)).andReturn(user)
        expect(repositoryContributorService.findByRepositoryAndUser(repositoryId, userId)).andReturn(Optional.empty())
        expect(repositoryContributorService.create(createRepositoryContributorDto)).andReturn(repositoryContributor)
        replayAll()
        repositoryServiceFacade.retrieveContributors(repositoryId)
                .apply { assertThat(this).containsExactly(user) }
        verifyAll()
    }

    @Test
    fun `test when fetched from remote and repository contributor found in local storage`() {
        val repositoryId = randomLong()
        val repository = repositoryCommonTestHelper.buildRepository().apply { this.id = repositoryId }
        val itemStatisticsViewModel = externalClientRestTestHelper.buildGitHubContributorsItemStatisticsViewModel()
        val resultResponse = externalClientRestTestHelper.buildGitHubContributorsStatisticsResultResponse(
                items = listOf(itemStatisticsViewModel)
        )
        val createOrUpdateUserDto = userCommonTestHelper.buildCreateOrUpdateUserDto(
                remoteId = itemStatisticsViewModel.user.id,
                login = itemStatisticsViewModel.user.login
        )
        val userId = randomLong()
        val user = userCommonTestHelper.buildUser().apply { this.id = userId }
        val repositoryContributor = repositoryContributorCommonTestHelper.buildRepositoryContributor()
        resetAll()
        expect(repositoryService.getById(repositoryId)).andReturn(repository)
        expect(remoteRepositoryService.retrieveContributors(repository.fullName)).andReturn(RemoteResultParams.ofSuccess(resultResponse))
        expect(userCompoundActionService.createOrUpdate(createOrUpdateUserDto)).andReturn(user)
        expect(repositoryContributorService.findByRepositoryAndUser(repositoryId, userId)).andReturn(Optional.of(repositoryContributor))
        replayAll()
        repositoryServiceFacade.retrieveContributors(repositoryId)
                .apply { assertThat(this).containsExactly(user) }
        verifyAll()
    }
}