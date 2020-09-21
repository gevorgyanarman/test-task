package com.test.task.facade.repository.impl;

import com.test.task.domain.respository.Repository;
import com.test.task.facade.repository.RepositoryFacade;
import com.test.task.facade.user.model.UserModelBuilder;
import com.test.task.domain.user.User;
import com.test.task.model.repository.error.RepositoryErrorResponseModel;
import com.test.task.model.repository.request.RepositorySearchRequest;
import com.test.task.model.repository.request.RetrieveRepositoryContributorsRequest;
import com.test.task.model.repository.response.*;
import com.test.task.platform.repository.RepositoryServiceFacade;
import com.test.task.platform.repository.params.RepositoriesSearchResultParams;
import com.test.task.service.repository.RepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
class RepositoryFacadeImpl implements RepositoryFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryFacadeImpl.class);

    private final RepositoryServiceFacade repositoryServiceFacade;
    private final RepositoryService repositoryService;
    private final RepositoryItemModelBuilder repositoryItemModelBuilder;
    private final UserModelBuilder userModelBuilder;

    RepositoryFacadeImpl(final RepositoryServiceFacade repositoryServiceFacade,
                         final RepositoryService repositoryService,
                         final RepositoryItemModelBuilder repositoryItemModelBuilder,
                         final UserModelBuilder userModelBuilder) {
        this.repositoryService = repositoryService;
        this.repositoryServiceFacade = repositoryServiceFacade;
        this.repositoryItemModelBuilder = repositoryItemModelBuilder;
        this.userModelBuilder = userModelBuilder;
        LOGGER.debug("Initializing - {}", getClass().getCanonicalName());
    }

    @Override
    public RepositorySearchResultResponse search(final RepositorySearchRequest request) {
        LOGGER.debug("Searching repositories for the provided request - {}", request);
        final RepositoriesSearchResultParams<Repository> searchResultParams = repositoryServiceFacade.search(request.getQuery());
        final List<RepositoryItemResponseModel> responseModels = searchResultParams.items().stream()
                .map(repositoryItemModelBuilder::build)
                .collect(Collectors.toList());
        final RepositoriesGridResponseModel gridResponseModel = new RepositoriesGridResponseModel(
                searchResultParams.totalCount(),
                responseModels
        );
        final RepositorySearchResultResponse resultResponse = new RepositorySearchResultResponse(gridResponseModel);
        LOGGER.debug("Successfully searched repositories for the provided request - {}, result - {}", request, resultResponse);
        return resultResponse;
    }


    @Override
    public RetrieveRepositoryContributorsResultResponse retrieveContributors(final RetrieveRepositoryContributorsRequest request) {
        LOGGER.debug("Retrieving repository users for the provided request - {}", request);
        final Optional<Repository> repository = repositoryService.findByUuId(request.getRepositoryUuId());
        if (!repository.isPresent()) {
            return new RetrieveRepositoryContributorsResultResponse(HttpStatus.NOT_FOUND.value(), RepositoryErrorResponseModel.REPOSITORY_NOT_FOUND);
        }
        final List<User> users = repositoryServiceFacade.retrieveContributors(repository.get().getId());
        final List<UserResponseModel> userModels = users.stream().map(userModelBuilder::build).collect(Collectors.toList());
        final RetrieveRepositoryContributorsGridResponseModel gridResponseModel = new RetrieveRepositoryContributorsGridResponseModel(
                userModels.size(),
                userModels
        );
        final RetrieveRepositoryContributorsResultResponse resultResponse = new RetrieveRepositoryContributorsResultResponse(gridResponseModel);
        LOGGER.debug(
                "Successfully retrieved repository users for the provided request - {}, result - {}",
                request,
                resultResponse
        );
        return resultResponse;
    }

}
