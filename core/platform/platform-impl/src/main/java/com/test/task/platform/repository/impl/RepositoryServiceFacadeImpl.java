package com.test.task.platform.repository.impl;

import com.test.task.domain.respository.Repository;
import com.test.task.domain.respository.RepositoryContributor;
import com.test.task.domain.user.User;
import com.test.task.github.model.search.GitHubRepositorySearchResultResponse;
import com.test.task.github.model.statistics.GitHubContributorUserViewModel;
import com.test.task.github.model.statistics.GitHubContributorsStatisticsResultResponse;
import com.test.task.platform.commons.RemoteResultParams;
import com.test.task.platform.repository.RepositoryServiceFacade;
import com.test.task.platform.repository.params.RepositoriesSearchResultParams;
import com.test.task.service.repository.RepositoryCompoundActionService;
import com.test.task.service.repository.RepositoryService;
import com.test.task.service.repository.dto.CreateOrUpdateRepositoryDto;
import com.test.task.service.repository.dto.SearchRepositoryDto;
import com.test.task.service.repository.user.RepositoryContributorService;
import com.test.task.service.repository.user.dto.CreateRepositoryContributorDto;
import com.test.task.service.user.UserCompoundActionService;
import com.test.task.service.user.dto.CreateOrUpdateUserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
class RepositoryServiceFacadeImpl implements RepositoryServiceFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryServiceFacadeImpl.class);

    private static final int SAVED_REPOSITORIES_FETCH_SIZE = 100;

    private final RepositoryService repositoryService;
    private final RepositoryCompoundActionService repositoryCompoundActionService;
    private final RemoteRepositoryService remoteRepositoryService;
    private final UserCompoundActionService userCompoundActionService;
    private final RepositoryContributorService repositoryContributorService;

    RepositoryServiceFacadeImpl(final RepositoryService repositoryService,
                                final RepositoryCompoundActionService repositoryCompoundActionService,
                                final RemoteRepositoryService remoteRepositoryService,
                                final UserCompoundActionService userCompoundActionService,
                                final RepositoryContributorService repositoryContributorService) {
        this.repositoryService = repositoryService;
        this.repositoryCompoundActionService = repositoryCompoundActionService;
        this.remoteRepositoryService = remoteRepositoryService;
        this.userCompoundActionService = userCompoundActionService;
        this.repositoryContributorService = repositoryContributorService;
        LOGGER.debug("Initializing - {}", getClass().getCanonicalName());
    }

    /**
     * Searches repositories for the provided query string in multiple sources if needed.
     *
     * First executes remote repository search and captures the remote results in local data store
     * If remote repository search is failed because of any reason, as an alternative solution
     * repositories are retrieved from local data store
     *
     * @param query the query text
     * @return repository search result params
     */
    @Override
    public RepositoriesSearchResultParams<Repository> search(final String query) {
        LOGGER.debug("Searching repositories in multiple sources if needed for the provided query - {}", query);
        Assert.hasText(query, "The repository search query should not be null or empty");
        final RemoteResultParams<GitHubRepositorySearchResultResponse> resultResponse = remoteRepositoryService.search(query);
        final RepositoriesSearchResultParams<Repository> resultParams = resultResponse.result()
                .map(this::createOrUpdateRemoteRepositories)
                .orElseGet(() -> findLocalRepositories(query));
        LOGGER.debug(
                "Successfully processed repositories search in multiple sources if needed for the provided query - {}, result - {}",
                query,
                resultParams
        );
        return resultParams;
    }

    /**
     * Retrieves contributor users for the provided repository in multiple sources if needed.
     *
     * First executes remote search and captures the remote results in local data store
     * If remote repository search is failed because of any reason, as an alternative solution
     * contributor users are retrieved from local data store
     *
     * @param repositoryId the repository Id
     * @return repository contributor users list
     */
    @Override
    public List<User> retrieveContributors(final Long repositoryId) {
        LOGGER.debug("Searching repository contributors in multiple sources if needed for the provided repository Id - {}", repositoryId);
        Assert.notNull(repositoryId, "The repository id should not be null");
        final Repository repository = repositoryService.getById(repositoryId);
        final String fullName = repository.getFullName();
        final RemoteResultParams<GitHubContributorsStatisticsResultResponse> resultResponse = remoteRepositoryService.retrieveContributors(fullName);
        final List<User> users = resultResponse.result()
                .map(getRemoteContributorsProcessingFunction(repositoryId))
                .orElseGet(() -> repositoryContributorService.findByRepository(repositoryId).stream().map(RepositoryContributor::getUser).collect(Collectors.toList()));
        LOGGER.debug(
                "Successfully processed repositories contributors search in multiple sources if needed for the provided repository Id - {}, result - {}",
                repositoryId,
                users
        );
        return users;
    }

    private Function<GitHubContributorsStatisticsResultResponse, List<User>> getRemoteContributorsProcessingFunction(final Long repositoryId) {
        return gitHubContributorsItemStatisticsViewModels -> gitHubContributorsItemStatisticsViewModels.stream().map(item -> {
            final GitHubContributorUserViewModel userModel = item.getUser();
            final User user = userCompoundActionService.createOrUpdate(new CreateOrUpdateUserDto(userModel.getId(), userModel.getLogin()));
            if (!repositoryContributorService.findByRepositoryAndUser(repositoryId, user.getId()).isPresent()) {
                repositoryContributorService.create(new CreateRepositoryContributorDto(user.getId(), repositoryId));
            }
            return user;
        }).collect(Collectors.toList());
    }

    private RepositoriesSearchResultParams<Repository> findLocalRepositories(final String query) {
        final Page<Repository> repositories = repositoryService.search(new SearchRepositoryDto(SAVED_REPOSITORIES_FETCH_SIZE, query));
        return RepositoriesSearchResultParams.of(repositories.getTotalElements(), repositories.toList());
    }

    private RepositoriesSearchResultParams<Repository> createOrUpdateRemoteRepositories(final GitHubRepositorySearchResultResponse remoteRepositories) {
        final List<Repository> repositories = remoteRepositories.getItems().stream()
                .map(model -> new CreateOrUpdateRepositoryDto(model.getId(), model.getName(), model.getFullName()))
                .map(repositoryCompoundActionService::createOrUpdate)
                .collect(Collectors.toList());
        return RepositoriesSearchResultParams.of(remoteRepositories.getTotalCount(), repositories);
    }

}
