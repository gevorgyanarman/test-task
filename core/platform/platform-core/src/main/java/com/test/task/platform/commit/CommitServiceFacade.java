package com.test.task.platform.commit;

import com.test.task.domain.respository.commit.Commit;

import java.util.List;


public interface CommitServiceFacade {

    /**
     * Return commits made in the give repository
     *
     * @param repositoryId the repository Id
     * @return list of commits made in the provided repository
     */
    List<Commit> retrieve(final Long repositoryId);
}
