package com.test.task.platform.repository.impl;

import com.test.task.github.model.search.GitHubRepositorySearchResultResponse;
import com.test.task.github.model.statistics.GitHubContributorsStatisticsResultResponse;
import com.test.task.platform.commons.RemoteResultParams;


interface RemoteRepositoryService {

    /**
     * Searches repositories in github for the provided query text
     *
     * @param query the repository full name
     * @return remote result params of remote repositories
     */
    RemoteResultParams<GitHubRepositorySearchResultResponse> search(final String query);

    /**
     * Retrieve repository contributors in github for the provided repository full name
     *
     * @param fullName the repository full name
     * @return remote result params of remote repository contributors
     */
    RemoteResultParams<GitHubContributorsStatisticsResultResponse> retrieveContributors(final String fullName);
}
