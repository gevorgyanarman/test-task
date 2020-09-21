package com.test.task.platform.commit.impl

import com.test.task.platform.commons.RemoteResultParams
import org.assertj.core.api.Assertions.assertThat
import org.easymock.EasyMock.expect
import org.junit.Test
import java.util.*

class CommitRetrieveServiceFacadeImplUnitTest : AbstractCommitServiceFacadeImplUnitTest() {

    @Test
    fun `test with invalid argument`() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { commitServiceFacade.retrieve(null) }
        verifyAll()
    }

    @Test
    fun `test when not fetched from remote`() {
        val repositoryId = randomLong()
        val repository = repositoryCommonTestHelper.buildRepository()
        val commits = listOf(commitCommonTestHelper.buildCommit(), commitCommonTestHelper.buildCommit())
        resetAll()
        expect(repositoryService.getById(repositoryId)).andReturn(repository)
        expect(remoteCommitsRetrievalService.retrieve(repository.fullName)).andReturn(RemoteResultParams.ofError())
        expect(commitService.findAllByRepository(repositoryId)).andReturn(commits)
        replayAll()
        commitServiceFacade.retrieve(repositoryId)
                .apply { assertThat(this).containsExactlyElementsOf(commits) }
        verifyAll()
    }

    @Test
    fun `test when fetched from remote but commit not found`() {
        val repositoryId = randomLong()
        val repository = repositoryCommonTestHelper.buildRepository().apply { this.id = repositoryId }
        val commitsItemResponseModel = externalClientRestTestHelper.buildGitHubCommitsItemResponseModel()
        val resultResponse = externalClientRestTestHelper.buildGitHubRetrieveCommitsResultResponse(
                items = listOf(commitsItemResponseModel)
        )
        val createOrUpdateUserDto = userCommonTestHelper.buildCreateOrUpdateUserDto(
                remoteId = commitsItemResponseModel.author.get().id,
                login = commitsItemResponseModel.author.get().login
        )
        val userId = randomLong()
        val user = userCommonTestHelper.buildUser().apply { this.id = userId }
        val createCommitDto = commitCommonTestHelper.buildCreateCommitDto(
                userId = userId,
                repositoryId = repository.id,
                sha = commitsItemResponseModel.sha,
                remoteCreationDate = commitsItemResponseModel.commit.author.dateTime
        )
        val commit = commitCommonTestHelper.buildCommit()
        resetAll()
        expect(repositoryService.getById(repositoryId)).andReturn(repository)
        expect(remoteCommitsRetrievalService.retrieve(repository.fullName)).andReturn(RemoteResultParams.ofSuccess(resultResponse))
        expect(commitService.findBySha(commitsItemResponseModel.sha)).andReturn(Optional.empty())
        expect(userCompoundActionService.createOrUpdate(createOrUpdateUserDto)).andReturn(user)
        expect(commitService.create(createCommitDto)).andReturn(commit)
        replayAll()
        commitServiceFacade.retrieve(repositoryId)
                .apply { assertThat(this.size).isEqualTo(1) }
                .apply { assertThat(this).containsExactly(commit) }
        verifyAll()
    }

    @Test
    fun `test when fetched from remote but commit and author user not found`() {
        val repositoryId = randomLong()
        val repository = repositoryCommonTestHelper.buildRepository().apply { this.id = repositoryId }
        val commitsItemResponseModel = externalClientRestTestHelper.buildGitHubCommitsItemResponseModel(author = null)
        val resultResponse = externalClientRestTestHelper.buildGitHubRetrieveCommitsResultResponse(
                items = listOf(commitsItemResponseModel)
        )
        val createOrUpdateUserDto = userCommonTestHelper.buildCreateOrUpdateUserDto(
                remoteId = CommitServiceFacadeImpl.UNKNOWN_USER_REMOTE_ID,
                login = CommitServiceFacadeImpl.UNKNOWN_USER_LOGIN
        )
        val userId = randomLong()
        val user = userCommonTestHelper.buildUser().apply { this.id = userId }
        val createCommitDto = commitCommonTestHelper.buildCreateCommitDto(
                userId = userId,
                repositoryId = repository.id,
                sha = commitsItemResponseModel.sha,
                remoteCreationDate = commitsItemResponseModel.commit.author.dateTime
        )
        val commit = commitCommonTestHelper.buildCommit()
        resetAll()
        expect(repositoryService.getById(repositoryId)).andReturn(repository)
        expect(remoteCommitsRetrievalService.retrieve(repository.fullName)).andReturn(RemoteResultParams.ofSuccess(resultResponse))
        expect(commitService.findBySha(commitsItemResponseModel.sha)).andReturn(Optional.empty())
        expect(userCompoundActionService.createOrUpdate(createOrUpdateUserDto)).andReturn(user)
        expect(commitService.create(createCommitDto)).andReturn(commit)
        replayAll()
        commitServiceFacade.retrieve(repositoryId)
                .apply { assertThat(this.size).isEqualTo(1) }
                .apply { assertThat(this).containsExactly(commit) }
        verifyAll()
    }

    @Test
    fun `test when fetched from remote and commit found`() {
        val repositoryId = randomLong()
        val repository = repositoryCommonTestHelper.buildRepository().apply { this.id = repositoryId }
        val commitsItemResponseModel = externalClientRestTestHelper.buildGitHubCommitsItemResponseModel()
        val resultResponse = externalClientRestTestHelper.buildGitHubRetrieveCommitsResultResponse(
                items = listOf(commitsItemResponseModel)
        )
        val commit = commitCommonTestHelper.buildCommit()
        resetAll()
        expect(repositoryService.getById(repositoryId)).andReturn(repository)
        expect(remoteCommitsRetrievalService.retrieve(repository.fullName)).andReturn(RemoteResultParams.ofSuccess(resultResponse))
        expect(commitService.findBySha(commitsItemResponseModel.sha)).andReturn(Optional.of(commit))
        replayAll()
        commitServiceFacade.retrieve(repositoryId)
                .apply { assertThat(this.size).isEqualTo(1) }
                .apply { assertThat(this).containsExactly(commit) }
        verifyAll()
    }
}