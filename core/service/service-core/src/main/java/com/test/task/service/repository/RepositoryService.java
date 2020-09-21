package com.test.task.service.repository;

import com.test.task.domain.respository.Repository;
import com.test.task.service.repository.dto.CreateRepositoryDto;
import com.test.task.service.repository.dto.SearchRepositoryDto;
import com.test.task.service.repository.dto.UpdateRepositoryDto;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface RepositoryService {

    Repository create(final CreateRepositoryDto dto);

    Optional<Repository> findById(final Long id);

    Repository getById(final Long id);

    Optional<Repository> findByUuId(final String uuId);

    Optional<Repository> findByRemoteId(final String remoteId);

    Page<Repository> search(final SearchRepositoryDto dto);

    Repository update(final UpdateRepositoryDto dto);
}
