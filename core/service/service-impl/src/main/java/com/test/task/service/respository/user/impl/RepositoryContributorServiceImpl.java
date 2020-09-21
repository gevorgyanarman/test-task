package com.test.task.service.respository.user.impl;

import com.test.task.domain.respository.Repository;
import com.test.task.domain.respository.RepositoryContributor;
import com.test.task.domain.user.User;
import com.test.task.repository.repository.contributor.RepositoryContributorRepository;
import com.test.task.service.commons.EntityNotFoundForIdException;
import com.test.task.service.repository.RepositoryService;
import com.test.task.service.repository.user.RepositoryContributorService;
import com.test.task.service.repository.user.dto.CreateRepositoryContributorDto;
import com.test.task.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;


@Service
class RepositoryContributorServiceImpl implements RepositoryContributorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryContributorServiceImpl.class);

    private final UserService userService;
    private final RepositoryService repositoryService;
    private final RepositoryContributorRepository repositoryContributorRepository;

    RepositoryContributorServiceImpl(final UserService userService,
                                     final RepositoryService repositoryService,
                                     final RepositoryContributorRepository repositoryContributorRepository) {
        this.userService = userService;
        this.repositoryService = repositoryService;
        this.repositoryContributorRepository = repositoryContributorRepository;
        LOGGER.debug("Initializing - {}", getClass().getCanonicalName());
    }

    @Transactional
    @Override
    public RepositoryContributor create(final CreateRepositoryContributorDto dto) {
        LOGGER.debug("Creating repository contributor for the provided dto - {}", dto);
        Assert.notNull(dto, "The repository contributor creation dto should not be null");
        final User user = userService.getById(dto.getUserId());
        final Repository repository = repositoryService.getById(dto.getRepositoryId());
        final RepositoryContributor repositoryContributor = repositoryContributorRepository.save(
                new RepositoryContributor(repository, user)
        );
        LOGGER.debug("Successfully created repository contributor for the provided dto - {}, result - {}", dto, repositoryContributor);
        return repositoryContributor;
    }

    @Transactional(readOnly = true)
    @Override
    public RepositoryContributor getById(final Long id) {
        LOGGER.debug("Retrieving repository contributor for the provided id - {}", id);
        Assert.notNull(id, "The repository contributor id should not be null");
        final RepositoryContributor repositoryContributor = repositoryContributorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundForIdException(id, RepositoryContributor.class));
        LOGGER.debug("Successfully retrieved repository contributor for the provided id - {}, result - {}", id, repositoryContributor);
        return repositoryContributor;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<RepositoryContributor> findByRepositoryAndUser(final Long repositoryId, final Long userId) {
        LOGGER.debug("Finding repository contributor for the provided repository Id - {} and user Id - {}", repositoryId, userId);
        Assert.notNull(repositoryId, "The repository id should not be null");
        Assert.notNull(userId, "The user id should not be null");
        final Optional<RepositoryContributor> repositoryContributorOptional = repositoryContributorRepository
                .findByRepositoryIdAndUserId(repositoryId, userId);
        LOGGER.debug(
                "Successfully processed repository contributor lookup for the provided repository Id - {} and user Id - {}, result - {}",
                repositoryId,
                userId,
                repositoryContributorOptional
        );
        return repositoryContributorOptional;
    }

    @Transactional(readOnly = true)
    @Override
    public List<RepositoryContributor> findByRepository(final Long repositoryId) {
        LOGGER.debug("Finding repository contributor for the provided repository Id - {}", repositoryId);
        Assert.notNull(repositoryId, "The repository id should not be null");
        final List<RepositoryContributor> repositoryContributors = repositoryContributorRepository.findByRepositoryId(repositoryId);
        LOGGER.debug(
                "Successfully processed repository user lookup for the provided repository Id - {}, result - {}",
                repositoryId,
                repositoryContributors
        );
        return repositoryContributors;
    }
}
