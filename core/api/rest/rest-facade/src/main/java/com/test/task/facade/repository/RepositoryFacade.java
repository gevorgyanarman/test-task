package com.test.task.facade.repository;

import com.test.task.model.repository.request.RepositorySearchRequest;
import com.test.task.model.repository.response.RepositorySearchResultResponse;
import com.test.task.model.repository.request.RetrieveRepositoryContributorsRequest;
import com.test.task.model.repository.response.RetrieveRepositoryContributorsResultResponse;

public interface RepositoryFacade {

    /**
     * Search repositories for the provided search request
     *
     * @param request the search request
     * @return the repository search result response
     */
    RepositorySearchResultResponse search(final RepositorySearchRequest request);

    /**
     * Retrieves repository contributors for the provided request
     *
     * @param request the retrieval request
     * @return the repository contributors retrieval result response
     */
    RetrieveRepositoryContributorsResultResponse retrieveContributors(final RetrieveRepositoryContributorsRequest request);
}
