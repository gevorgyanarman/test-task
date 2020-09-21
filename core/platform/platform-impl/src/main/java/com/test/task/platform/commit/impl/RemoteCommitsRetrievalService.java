package com.test.task.platform.commit.impl;

import com.test.task.github.model.commit.GitHubRetrieveCommitsResultResponse;
import com.test.task.platform.commons.RemoteResultParams;


interface RemoteCommitsRetrievalService {

    /**
     * Retrieves part of commits from github for the provided repository full name
     *
     * @param fullName the repository full name
     * @return remote result params of remote commits
     */
    RemoteResultParams<GitHubRetrieveCommitsResultResponse> retrieve(final String fullName);
}
