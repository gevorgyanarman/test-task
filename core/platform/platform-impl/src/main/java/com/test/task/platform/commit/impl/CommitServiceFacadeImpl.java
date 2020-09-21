package com.test.task.platform.commit.impl;

import com.test.task.domain.respository.Repository;
import com.test.task.domain.respository.commit.Commit;
import com.test.task.github.model.commit.GitHubAuthorResponseModel;
import com.test.task.github.model.commit.GitHubRetrieveCommitsResultResponse;
import com.test.task.domain.user.User;
import com.test.task.platform.commit.CommitServiceFacade;
import com.test.task.platform.commons.RemoteResultParams;
import com.test.task.service.commit.CommitService;
import com.test.task.service.commit.dto.CreateCommitDto;
import com.test.task.service.repository.RepositoryService;
import com.test.task.service.user.UserCompoundActionService;
import com.test.task.service.user.dto.CreateOrUpdateUserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
class CommitServiceFacadeImpl implements CommitServiceFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommitServiceFacadeImpl.class);
    static final String UNKNOWN_USER_REMOTE_ID = "UNKNOWN_USER_REMOTE_ID";
    static final String UNKNOWN_USER_LOGIN = "UNKNOWN_USER_LOGIN";

    private final RepositoryService repositoryService;
    private final UserCompoundActionService userCompoundActionService;
    private final CommitService commitService;
    private final RemoteCommitsRetrievalService remoteCommitsRetrievalService;

    CommitServiceFacadeImpl(final RepositoryService repositoryService,
                            final UserCompoundActionService userCompoundActionService,
                            final CommitService commitService,
                            final RemoteCommitsRetrievalService remoteCommitsRetrievalService) {
        this.repositoryService = repositoryService;
        this.userCompoundActionService = userCompoundActionService;
        this.commitService = commitService;
        this.remoteCommitsRetrievalService = remoteCommitsRetrievalService;
        LOGGER.debug("Initializing - {}", getClass().getCanonicalName());
    }

    /**
     * Searches commits for the provided repository in multiple sources if needed.
     *
     * First executes remote repository search and captures the remote results in local data store
     * If remote repository search is failed because of any reason, as an alternative solution
     * commits are retrieved from local data store
     *
     * @param repositoryId the repository Id
     * @return list of commits made in the provided repository
     */
    @Override
    public List<Commit> retrieve(final Long repositoryId) {
        LOGGER.debug("Searching commits in multiple sources if needed for the provided repository Id - {}", repositoryId);
        Assert.notNull(repositoryId, "The repository id should not be null");
        final Repository repository = repositoryService.getById(repositoryId);
        final String fullName = repository.getFullName();
        final RemoteResultParams<GitHubRetrieveCommitsResultResponse> resultParams = remoteCommitsRetrievalService.retrieve(fullName);
        final List<Commit> commits = resultParams.result().map(resultResponse -> processRemoteResponse(resultResponse, repositoryId))
                .orElseGet(() -> commitService.findAllByRepository(repositoryId));
        LOGGER.debug(
                "Successfully processed commits search in multiple sources if needed for the provided repository Id - {}, result - {}",
                repository,
                commits
        );
        return commits;
    }

    private List<Commit> processRemoteResponse(final GitHubRetrieveCommitsResultResponse resultResponse, final Long repositoryId) {
        return resultResponse.stream().map(item -> {
            final String sha = item.getSha();
            final Optional<Commit> commitOptional = commitService.findBySha(sha);
            if (!commitOptional.isPresent()) {
                final LocalDateTime remoteCreationDateTime = item.getCommit().getAuthor().getDateTime();
                final User user = createOrUpdateUser(item.getAuthor().orElse(null));
                return commitService.create(new CreateCommitDto(user.getId(), repositoryId, sha, remoteCreationDateTime));
            }
            return commitOptional.get();
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    private User createOrUpdateUser(@Nullable final GitHubAuthorResponseModel gitHubAuthorResponseModel) {
        final CreateOrUpdateUserDto dto;
        if (Objects.nonNull(gitHubAuthorResponseModel)) {
            final String userRemoteId = gitHubAuthorResponseModel.getId();
            final String userLogin = gitHubAuthorResponseModel.getLogin();
            dto = new CreateOrUpdateUserDto(userRemoteId, userLogin);
        } else {
            dto = new CreateOrUpdateUserDto(UNKNOWN_USER_REMOTE_ID, UNKNOWN_USER_LOGIN);
        }
        return userCompoundActionService.createOrUpdate(dto);
    }
}
