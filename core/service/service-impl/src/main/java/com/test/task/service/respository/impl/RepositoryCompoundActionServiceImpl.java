package com.test.task.service.respository.impl;

import com.test.task.domain.respository.Repository;
import com.test.task.service.repository.RepositoryCompoundActionService;
import com.test.task.service.repository.RepositoryService;
import com.test.task.service.repository.dto.CreateOrUpdateRepositoryDto;
import com.test.task.service.repository.dto.CreateRepositoryDto;
import com.test.task.service.repository.dto.UpdateRepositoryDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
class RepositoryCompoundActionServiceImpl implements RepositoryCompoundActionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryCompoundActionServiceImpl.class);

    private final RepositoryService repositoryService;

    RepositoryCompoundActionServiceImpl(final RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
        LOGGER.debug("Initializing - {}", getClass().getCanonicalName());
    }

    @Transactional
    @Override
    public Repository createOrUpdate(final CreateOrUpdateRepositoryDto dto) {
        LOGGER.debug("Processing repository create or update for the provided dto - {}", dto);
        Assert.notNull(dto, "the repository create or update dto should not be null");
        final Optional<Repository> oldRepository = repositoryService.findByRemoteId(dto.getRemoteId());
        final Repository repository = oldRepository.isPresent() ?
                repositoryService.update(
                        new UpdateRepositoryDto(
                                oldRepository.get().getId(),
                                dto.getName(),
                                dto.getFullName()
                        )
                )
                : repositoryService.create(new CreateRepositoryDto(dto.getRemoteId(), dto.getName(), dto.getFullName()));
        LOGGER.debug(
                "Successfully processed repository create or update for the provided dto - {}, result - {}",
                dto,
                repository
        );
        return repository;
    }
}
