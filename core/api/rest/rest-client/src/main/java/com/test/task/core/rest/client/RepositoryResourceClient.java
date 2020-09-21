package com.test.task.core.rest.client;

import com.test.task.model.repository.response.RepositorySearchResultResponse;
import com.test.task.model.repository.response.RetrieveRepositoryContributorsResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "coreRepositoryResourceClient", path = "repositories", url = "${ms.core.url}")
public interface RepositoryResourceClient {

    @GetMapping(path = "/search")
    ResponseEntity<RepositorySearchResultResponse> search(@RequestParam("query") final String query);

    @GetMapping(path = "/{uuId}/users")
    ResponseEntity<RetrieveRepositoryContributorsResultResponse> retrieveUsers(@PathVariable("uuId") final String uuId);
}
