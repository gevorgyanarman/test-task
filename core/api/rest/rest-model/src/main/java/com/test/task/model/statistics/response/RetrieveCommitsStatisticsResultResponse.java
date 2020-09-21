package com.test.task.model.statistics.response;

import com.test.task.commons.api.model.model.response.impl.AbstractResultResponseModel;
import com.test.task.model.statistics.error.StatisticsErrorResponseModel;

import java.util.List;


public class RetrieveCommitsStatisticsResultResponse extends AbstractResultResponseModel<CommitsStatisticsResponseModel, StatisticsErrorResponseModel> {

    public RetrieveCommitsStatisticsResultResponse() {
        super();
    }

    public RetrieveCommitsStatisticsResultResponse(final int httpStatusCode, final StatisticsErrorResponseModel error) {
        super(httpStatusCode, error);
    }

    public RetrieveCommitsStatisticsResultResponse(final int httpStatusCode, final List<StatisticsErrorResponseModel> errors) {
        super(httpStatusCode, errors);
    }

    public RetrieveCommitsStatisticsResultResponse(final CommitsStatisticsResponseModel response) {
        super(response);
    }
}
