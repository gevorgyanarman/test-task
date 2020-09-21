package com.test.task.rest.resource;

import com.test.task.facade.repository.RepositoryFacade;
import com.test.task.model.repository.request.RepositorySearchRequest;
import com.test.task.model.repository.response.RepositorySearchResultResponse;
import com.test.task.model.repository.request.RetrieveRepositoryContributorsRequest;
import com.test.task.model.repository.response.RetrieveRepositoryContributorsResultResponse;
import com.test.task.rest.resource.utils.ResponseEntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/repositories", produces = MediaType.APPLICATION_JSON_VALUE)
public class RepositoriesResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoriesResource.class);

    private final RepositoryFacade repositoryFacade;

    public RepositoriesResource(final RepositoryFacade repositoryFacade) {
        this.repositoryFacade = repositoryFacade;
        LOGGER.debug("Initializing - {}", getClass().getCanonicalName());
    }

    @GetMapping(path = "/search")
    public ResponseEntity<RepositorySearchResultResponse> search(@RequestParam("query") final String query) {
        LOGGER.debug("Processing repositories resource search for the provided query param - {}", query);
        final RepositorySearchResultResponse resultResponse = repositoryFacade.search(new RepositorySearchRequest(query));
        LOGGER.debug(
                "Successfully processed repositories resource search for the provided query param - {}, result - {}",
                query,
                resultResponse
        );
        return ResponseEntityUtils.okWithStatusInHeader(resultResponse);
    }

    @GetMapping(path = "/{uuId}/users")
    public ResponseEntity<RetrieveRepositoryContributorsResultResponse> retrieveUsers(@PathVariable("uuId") final String uuId) {
        LOGGER.debug("Processing repositories resource repository users retrieval for the provided uuId - {}", uuId);
        final RetrieveRepositoryContributorsResultResponse resultResponse = repositoryFacade.retrieveContributors(new RetrieveRepositoryContributorsRequest(uuId));
        LOGGER.debug(
                "Successfully processed repositories resource repository users retrieval for the provided uuId - {}, result - {}",
                uuId,
                resultResponse
        );
        return ResponseEntityUtils.okWithStatusInHeader(resultResponse);
    }
}
