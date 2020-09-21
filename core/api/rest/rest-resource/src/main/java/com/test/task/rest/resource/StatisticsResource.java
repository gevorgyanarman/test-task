package com.test.task.rest.resource;

import com.test.task.facade.statistics.StatisticsFacade;
import com.test.task.model.statistics.request.RetrieveCommitsStatisticsRequest;
import com.test.task.model.statistics.response.RetrieveCommitsStatisticsResultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "/statistics", produces = MediaType.APPLICATION_JSON_VALUE)
public class StatisticsResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsResource.class);

    private final StatisticsFacade statisticsFacade;

    public StatisticsResource(final StatisticsFacade statisticsFacade) {
        this.statisticsFacade = statisticsFacade;
        LOGGER.debug("Initializing - {}", getClass().getCanonicalName());
    }

    @GetMapping(path = "/{repositoryUuId}/commits")
    public ResponseEntity<RetrieveCommitsStatisticsResultResponse> retrieveCommits(@PathVariable("repositoryUuId") final String repositoryUuId) {
        LOGGER.debug("Processing statistics resource repository users retrieval for the provided uuId - {}", repositoryUuId);
        final RetrieveCommitsStatisticsResultResponse resultResponse = statisticsFacade.retrieveCommits(
                new RetrieveCommitsStatisticsRequest(repositoryUuId)
        );
        LOGGER.debug(
                "Successfully processed repositories resource repository users retrieval for the provided uuId - {}, result - {}",
                repositoryUuId,
                resultResponse
        );
        return ResponseEntity.ok(resultResponse);
    }
}