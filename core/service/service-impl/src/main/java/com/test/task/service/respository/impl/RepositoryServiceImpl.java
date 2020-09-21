package com.test.task.service.respository.impl;

import com.test.task.domain.respository.Repository;
import com.test.task.repository.repository.RepositoryRepository;
import com.test.task.service.commons.EntityNotFoundForIdException;
import com.test.task.service.repository.RepositoryService;
import com.test.task.service.repository.dto.CreateRepositoryDto;
import com.test.task.service.repository.dto.SearchRepositoryDto;
import com.test.task.service.repository.dto.UpdateRepositoryDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Optional;


@Service
class RepositoryServiceImpl implements RepositoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryServiceImpl.class);

    private final RepositoryRepository repositoryRepository;

    RepositoryServiceImpl(final RepositoryRepository repositoryRepository) {
        this.repositoryRepository = repositoryRepository;
        LOGGER.debug("Initializing - {}", getClass().getCanonicalName());
    }

    @Transactional
    @Override
    public Repository create(final CreateRepositoryDto dto) {
        LOGGER.debug("Creating repository for the provided dto - {}", dto);
        Assert.notNull(dto, "The repository create dto should not be null");
        final Repository repository = repositoryRepository.save(
                new Repository(dto.getRemoteId(), dto.getName(), dto.getFullName())
        );
        LOGGER.debug("Successfully created repository for the provided dto - {}", dto);
        return repository;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Repository> findById(final Long id) {
        LOGGER.debug("Finding repository for the provided id - {}", id);
        Assert.notNull(id, "The repository id should not be null");
        final Optional<Repository> repositoryOptional = repositoryRepository.findById(id);
        LOGGER.debug("Successfully processed repository lookup for the provided id - {}, result - {}", id, repositoryOptional);
        return repositoryOptional;
    }

    @Transactional(readOnly = true)
    @Override
    public Repository getById(final Long id) {
        LOGGER.debug("Retrieving repository for the provided id - {}", id);
        Assert.notNull(id, "The repository id should not be null");
        final Repository repository = repositoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundForIdException(id, Repository.class));
        LOGGER.debug("Successfully retrieved repository for the provided id - {}, result - {}", id, repository);
        return repository;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Repository> findByUuId(final String uuId) {
        LOGGER.debug("Finding repository for the provided uuId - {}", uuId);
        Assert.hasText(uuId, "The repository uuId should not be null or empty");
        final Optional<Repository> repositoryOptional = repositoryRepository.findByUuId(uuId);
        LOGGER.debug(
                "Successfully processed repository lookup for the provided uuId - {}, result - {}",
                uuId,
                repositoryOptional
        );
        return repositoryOptional;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Repository> findByRemoteId(final String remoteId) {
        LOGGER.debug("Finding repository for the provided remote id - {}", remoteId);
        Assert.hasText(remoteId, "The remote id should not be null or empty");
        final Optional<Repository> repositoryOptional = repositoryRepository.findByRemoteId(remoteId);
        LOGGER.debug(
                "Successfully processed repository lookup for the provided remote id - {}, result - {}",
                remoteId,
                repositoryOptional
        );
        return repositoryOptional;
    }

    @Transactional
    @Override
    public Repository update(final UpdateRepositoryDto dto) {
        LOGGER.debug("Updating repository for the provided dto - {}", dto);
        Assert.notNull(dto, "The repository update dto should not be null");
        final Repository repository = getById(dto.getId());
        repository.setFullName(dto.getFullName());
        repository.setName(dto.getName());
        final Repository updatedRepository = repositoryRepository.save(repository);
        LOGGER.debug("Successfully updated repository for the provided dto - {}, result - {}", dto, updatedRepository);
        return updatedRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Repository> search(final SearchRepositoryDto dto) {
        LOGGER.debug("Retrieving repositories for the provided dto - {}", dto);
        Assert.notNull(dto, "The repository search dto should not be null");
        final Page<Repository> repositories = repositoryRepository.search(dto.getQuery(), PageRequest.of(dto.getPage(), dto.getSize()));
        LOGGER.debug("Successfully retrieved repositories for the provided dto - {}, result - {}", dto, repositories);
        return repositories;
    }
}
