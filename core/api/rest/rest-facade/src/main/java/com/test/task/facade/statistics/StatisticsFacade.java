package com.test.task.facade.statistics;

import com.test.task.model.statistics.request.RetrieveCommitsStatisticsRequest;
import com.test.task.model.statistics.response.RetrieveCommitsStatisticsResultResponse;

public interface StatisticsFacade {

    /**
     * Retrieves repository commits statistics for the provided request
     *
     * @param request the statistics retrieval request
     * @return the repository commits statistics retrieval result response
     */
    RetrieveCommitsStatisticsResultResponse retrieveCommits(final RetrieveCommitsStatisticsRequest request);
}
