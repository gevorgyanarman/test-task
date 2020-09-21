package com.test.task.facade.statistics.impl;

import com.test.task.domain.respository.Repository;
import com.test.task.domain.respository.commit.Commit;
import com.test.task.facade.user.model.UserModelBuilder;
import com.test.task.facade.statistics.StatisticsFacade;
import com.test.task.model.statistics.response.CommitsStatisticsItemResponseModel;
import com.test.task.model.statistics.response.CommitsStatisticsResponseModel;
import com.test.task.model.statistics.request.RetrieveCommitsStatisticsRequest;
import com.test.task.model.statistics.response.RetrieveCommitsStatisticsResultResponse;
import com.test.task.model.statistics.error.StatisticsErrorResponseModel;
import com.test.task.domain.user.User;
import com.test.task.platform.commit.CommitServiceFacade;
import com.test.task.service.repository.RepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
class StatisticsFacadeImpl implements StatisticsFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsFacadeImpl.class);

    private final RepositoryService repositoryService;
    private final CommitServiceFacade commitServiceFacade;
    private final UserModelBuilder userModelBuilder;

    StatisticsFacadeImpl(final RepositoryService repositoryService,
                         final CommitServiceFacade commitServiceFacade,
                         final UserModelBuilder userModelBuilder) {
        this.repositoryService = repositoryService;
        this.commitServiceFacade = commitServiceFacade;
        this.userModelBuilder = userModelBuilder;
        LOGGER.debug("Initializing - {}", getClass().getCanonicalName());
    }

    @Transactional
    @Override
    public RetrieveCommitsStatisticsResultResponse retrieveCommits(final RetrieveCommitsStatisticsRequest request) {
        LOGGER.debug("Retrieving commits statistics for the provided request - {}", request);
        final Optional<Repository> repositoryOptional = repositoryService.findByUuId(request.getRepositoryUuId());
        if (!repositoryOptional.isPresent()) {
            return new RetrieveCommitsStatisticsResultResponse(HttpStatus.NOT_FOUND.value(), StatisticsErrorResponseModel.REPOSITORY_NOT_FOUND);
        }
        final List<Commit> commits = commitServiceFacade.retrieve(repositoryOptional.get().getId());
        final Map<User, Long> userCommitsCount = commits.stream().collect(Collectors.groupingBy(Commit::getUser, Collectors.counting()));
        final List<CommitsStatisticsItemResponseModel> itemResponseModels = userCommitsCount.entrySet().stream()
                .map(entry -> new CommitsStatisticsItemResponseModel(userModelBuilder.build(entry.getKey()), entry.getValue()))
                .collect(Collectors.toList());
        final RetrieveCommitsStatisticsResultResponse resultResponse = new RetrieveCommitsStatisticsResultResponse(
                new CommitsStatisticsResponseModel(itemResponseModels, commits.size())
        );
        LOGGER.debug(
                "Successfully retrieved commits statistics for the provided request - {}, result - {}",
                request,
                resultResponse
        );
        return resultResponse;
    }
}
