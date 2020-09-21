package com.test.task.github.client;

import com.test.task.github.model.commit.GitHubRetrieveCommitsResultResponse;
import com.test.task.github.model.statistics.GitHubContributorsStatisticsResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(
        name = "githubRepositories",
        path = "repos",
        url = "${ms.github.url}"
)
public interface GitHubRepositoriesClient {

    @GetMapping("{fullName}/stats/contributors")
    GitHubContributorsStatisticsResultResponse retrieveContributors(@PathVariable("fullName") final String fullName);

    @GetMapping("{fullName}/commits?per_page=100")
    GitHubRetrieveCommitsResultResponse retrieveCommits(@PathVariable("fullName") final String fullName);
}
