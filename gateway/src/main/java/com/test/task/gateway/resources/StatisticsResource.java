package com.test.task.gateway.resources;

import com.test.task.core.rest.client.StatisticsResourceClient;
import com.test.task.gateway.resources.utils.ResponseEntityUtils;
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

    private final StatisticsResourceClient statisticsResourceClient;

    public StatisticsResource(final StatisticsResourceClient statisticsResourceClient) {
        this.statisticsResourceClient = statisticsResourceClient;
        LOGGER.debug("Initializing - {}", getClass().getCanonicalName());
    }

    @GetMapping(path = "/{repositoryUuId}/commits")
    public ResponseEntity<RetrieveCommitsStatisticsResultResponse> retrieveCommits(@PathVariable("repositoryUuId") final String repositoryUuId) {
        LOGGER.debug("Processing gateway statistics resource repository users retrieval for the provided uuId - {}", repositoryUuId);
        final ResponseEntity<RetrieveCommitsStatisticsResultResponse> responseEntity = statisticsResourceClient.retrieveCommits(repositoryUuId);
        LOGGER.debug(
                "Successfully processed gateway repositories resource repository users retrieval for the provided uuId - {}, result - {}",
                repositoryUuId,
                responseEntity
        );
        return ResponseEntityUtils.withCorrectStatus(responseEntity);
    }
}
