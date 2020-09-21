package com.test.task.gateway.resources;

import com.test.task.core.rest.client.RepositoryResourceClient;
import com.test.task.gateway.resources.utils.ResponseEntityUtils;
import com.test.task.model.repository.response.RepositorySearchResultResponse;
import com.test.task.model.repository.response.RetrieveRepositoryContributorsResultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/repositories", produces = MediaType.APPLICATION_JSON_VALUE)
public class RepositoryResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryResource.class);

    private final RepositoryResourceClient repositoryResourceClient;

    public RepositoryResource(final RepositoryResourceClient repositoryResourceClient) {
        this.repositoryResourceClient = repositoryResourceClient;
        LOGGER.debug("Initializing - {}", getClass().getCanonicalName());
    }

    @GetMapping(path = "/search")
    public ResponseEntity<RepositorySearchResultResponse> search(@RequestParam("query") final String query) {
        LOGGER.debug("Processing gateway repositories resource search for the provided query param - {}", query);
        final ResponseEntity<RepositorySearchResultResponse> responseEntity = repositoryResourceClient.search(query);
        LOGGER.debug(
                "Successfully processed gateway repositories resource search for the provided query param - {}, result - {}",
                query,
                responseEntity
        );
        return ResponseEntityUtils.withCorrectStatus(responseEntity);
    }

    @GetMapping(path = "/{uuId}/users")
    public ResponseEntity<RetrieveRepositoryContributorsResultResponse> retrieveUsers(@PathVariable("uuId") final String uuId) {
        LOGGER.debug("Processing gateway repositories resource repository users retrieval for the provided uuId - {}", uuId);
        final ResponseEntity<RetrieveRepositoryContributorsResultResponse> responseEntity = repositoryResourceClient.retrieveUsers(uuId);
        LOGGER.debug(
                "Successfully processed gateway repositories resource repository users retrieval for the provided uuId - {}, result - {}",
                uuId,
                responseEntity
        );
        return ResponseEntityUtils.withCorrectStatus(responseEntity);
    }
}
