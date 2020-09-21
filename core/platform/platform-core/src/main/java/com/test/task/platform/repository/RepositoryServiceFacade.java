package com.test.task.platform.repository;


import com.test.task.domain.respository.Repository;
import com.test.task.domain.user.User;
import com.test.task.platform.repository.params.RepositoriesSearchResultParams;

import java.util.List;

public interface RepositoryServiceFacade {

    /**
     * Search repositories for the the give query string
     *
     * @param query the search text
     * @return repository search result param
     */
    RepositoriesSearchResultParams<Repository> search(final String query);

    /**
     * Return contributors of the give repository
     *
     * @param repositoryId the repository Id
     * @return list of contributor users of the provided repository
     */
    List<User> retrieveContributors(final Long repositoryId);
}
