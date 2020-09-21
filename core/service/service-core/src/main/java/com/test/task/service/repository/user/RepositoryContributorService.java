package com.test.task.service.repository.user;

import com.test.task.domain.respository.RepositoryContributor;
import com.test.task.service.repository.user.dto.CreateRepositoryContributorDto;

import java.util.List;
import java.util.Optional;


public interface RepositoryContributorService {

    RepositoryContributor create(final CreateRepositoryContributorDto dto);

    RepositoryContributor getById(final Long id);

    Optional<RepositoryContributor> findByRepositoryAndUser(final Long repositoryId, final Long userId);

    List<RepositoryContributor> findByRepository(final Long repositoryId);
}
