package com.test.task.service.commit.impl;

import com.test.task.domain.respository.Repository;
import com.test.task.domain.respository.commit.Commit;
import com.test.task.domain.user.User;
import com.test.task.repository.commit.CommitRepository;
import com.test.task.service.commit.CommitService;
import com.test.task.service.commit.dto.CreateCommitDto;
import com.test.task.service.commons.EntityNotFoundForIdException;
import com.test.task.service.repository.RepositoryService;
import com.test.task.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
class CommitServiceImpl implements CommitService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommitServiceImpl.class);

    private final CommitRepository commitRepository;
    private final UserService userService;
    private final RepositoryService repositoryService;

    CommitServiceImpl(final CommitRepository commitRepository,
                      final UserService userService,
                      final RepositoryService repositoryService) {
        this.commitRepository = commitRepository;
        this.userService = userService;
        this.repositoryService = repositoryService;
        LOGGER.debug("Initializing - {}", getClass().getCanonicalName());
    }

    @Transactional
    @Override
    public Commit create(final CreateCommitDto dto) {
        LOGGER.debug("Creating commit for the provided dto - {}", dto);
        Assert.notNull(dto, "The commit creation dto should not be null");
        final User user = userService.getById(dto.getUserId());
        final Repository repository = repositoryService.getById(dto.getRepositoryId());
        final Commit commit = commitRepository.save(
                new Commit(
                        user, repository, dto.getSha(), dto.getRemoteCreationDate()
                )
        );
        LOGGER.debug("Successfully created repository for the provided dto - {}, result - {}", dto, commit);
        return commit;
    }

    @Transactional(readOnly = true)
    @Override
    public Commit getById(final Long id) {
        LOGGER.debug("Retrieving commit for the provided id - {}", id);
        Assert.notNull(id, "The commit id should not be null");
        final Commit commit = commitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundForIdException(id, Commit.class));
        LOGGER.debug("Successfully retrieved commit for the provided id - {}, result - {}", id, commit);
        return commit;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Commit> findAllByRepository(final Long repositoryId) {
        Assert.notNull(repositoryId, "The repository id should not be null");
        LOGGER.debug("Finding all commits for the provided repositoryId - {}", repositoryId);
        final List<Commit> commits = commitRepository.findAllByRepositoryId(repositoryId);
        LOGGER.debug(
                "Successfully processed all commits retrieval for the provided repositoryId - {}, result - {}",
                repositoryId,
                commits
        );
        return commits;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Commit> findBySha(final String sha) {
        LOGGER.debug("Finding commit for the provided sha - {}", sha);
        Assert.hasText(sha, "The commit sha should not be null or empty");
        final Optional<Commit> commitOptional = commitRepository.findBySha(sha);
        LOGGER.debug(
                "Successfully processed commit lookup for the provided sha - {}, result - {}",
                sha,
                commitOptional
        );
        return commitOptional;
    }
}
