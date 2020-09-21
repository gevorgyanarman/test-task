package com.test.task.rest.test.helper.external.client

import com.test.task.github.model.commit.*
import com.test.task.github.model.search.GitHubRepositoryItemViewModel
import com.test.task.github.model.search.GitHubRepositorySearchResultResponse
import com.test.task.github.model.statistics.GitHubContributorUserViewModel
import com.test.task.github.model.statistics.GitHubContributorsItemStatisticsViewModel
import com.test.task.github.model.statistics.GitHubContributorsStatisticsResultResponse
import com.test.task.github.model.statistics.GitHubWeeklyHashModel
import com.test.task.rest.test.helper.AbstractRestTestHelper
import java.time.LocalDateTime
import kotlin.random.Random


class ExternalClientRestTestHelper : AbstractRestTestHelper() {
    fun buildGitHubCommitsItemResponseModel(
            sha: String? = uuId(),
            commit: GitHubCommitViewResponseModel? = buildGitHubCommitViewResponseModel(),
            author: GitHubAuthorResponseModel? = buildGitHubAuthorResponseModel()
    ): GitHubCommitsItemResponseModel = GitHubCommitsItemResponseModel(sha, commit, author)

    fun buildGitHubRetrieveCommitsResultResponse(
            items: List<GitHubCommitsItemResponseModel> = listOf()
    ): GitHubRetrieveCommitsResultResponse = GitHubRetrieveCommitsResultResponse().apply { this.addAll(items) }

    fun buildGitHubRepositorySearchResultResponse(
            totalCount: Long? = randomLong(),
            incompleteResults: Boolean = Random.nextBoolean(),
            items: List<GitHubRepositoryItemViewModel> = listOf(buildGitHubRepositoryItemViewModel())
    ): GitHubRepositorySearchResultResponse = GitHubRepositorySearchResultResponse(totalCount, incompleteResults, items)

    fun buildGitHubRepositoryItemViewModel(
            id: String? = uuId(),
            name: String? = uuId(),
            fullName: String? = uuId()
    ): GitHubRepositoryItemViewModel = GitHubRepositoryItemViewModel(id, name, fullName)

    fun buildGitHubContributorsStatisticsResultResponse(
            items: List<GitHubContributorsItemStatisticsViewModel> = listOf(buildGitHubContributorsItemStatisticsViewModel())
    ): GitHubContributorsStatisticsResultResponse = GitHubContributorsStatisticsResultResponse().apply { addAll(items) }

    fun buildGitHubContributorsItemStatisticsViewModel(
            totalCount: Long? = randomLong(),
            weeklyHashModels: List<GitHubWeeklyHashModel> = listOf(),
            user: GitHubContributorUserViewModel = buildGitHubContributorUserViewModel()
    ): GitHubContributorsItemStatisticsViewModel = GitHubContributorsItemStatisticsViewModel(totalCount, weeklyHashModels, user)

    private fun buildGitHubCommitAuthorResponseModel(name: String? = uuId(),
                                                     dateTime: LocalDateTime? = LocalDateTime.now()
    ): GitHubCommitAuthorResponseModel = GitHubCommitAuthorResponseModel(name, dateTime)

    private fun buildGitHubAuthorResponseModel(id: String? = uuId(), login: String? = uuId()
    ): GitHubAuthorResponseModel = GitHubAuthorResponseModel(id, login)

    private fun buildGitHubCommitViewResponseModel(
            author: GitHubCommitAuthorResponseModel? = buildGitHubCommitAuthorResponseModel()
    ): GitHubCommitViewResponseModel = GitHubCommitViewResponseModel(author)

    private fun buildGitHubContributorUserViewModel(
            id: String? = uuId(),
            login: String? = uuId()
    ): GitHubContributorUserViewModel = GitHubContributorUserViewModel(id, login)
}