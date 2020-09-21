package com.test.task.core.rest.client;

import com.test.task.model.statistics.response.RetrieveCommitsStatisticsResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "coreStatisticsResourceClient", path = "statistics", url = "${ms.core.url}")
public interface StatisticsResourceClient {

    @GetMapping(path = "/{repositoryUuId}/commits")
    ResponseEntity<RetrieveCommitsStatisticsResultResponse> retrieveCommits(@PathVariable("repositoryUuId") final String repositoryUuId);
}
