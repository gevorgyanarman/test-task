package com.test.task.github.client;

import com.test.task.github.model.search.GitHubRepositorySearchResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(
        name = "githubSearches",
        path = "search",
        url = "${ms.github.url}"
)
public interface GitHubSearchClient {

    @GetMapping("repositories")
    GitHubRepositorySearchResultResponse search(@RequestParam("q") final String query);
}