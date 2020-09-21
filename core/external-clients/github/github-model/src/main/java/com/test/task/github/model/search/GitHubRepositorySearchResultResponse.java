package com.test.task.github.model.search;

import com.test.task.github.model.AbstractGridAwareResultResponse;

import java.util.List;


public class GitHubRepositorySearchResultResponse extends AbstractGridAwareResultResponse<GitHubRepositoryItemViewModel> {

    public GitHubRepositorySearchResultResponse() {
        super();
    }

    public GitHubRepositorySearchResultResponse(final Long totalCount,
                                                final Boolean incompleteResults,
                                                final List<GitHubRepositoryItemViewModel> items) {
        super(totalCount, incompleteResults, items);
    }
}
