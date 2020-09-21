package com.test.task.model.repository.response;

import com.test.task.commons.api.model.model.response.impl.AbstractResultResponseModel;
import com.test.task.model.repository.error.RepositoryErrorResponseModel;

import java.util.List;

public class RetrieveRepositoryContributorsResultResponse extends AbstractResultResponseModel<RetrieveRepositoryContributorsGridResponseModel, RepositoryErrorResponseModel> {

    public RetrieveRepositoryContributorsResultResponse() {
        super();
    }

    public RetrieveRepositoryContributorsResultResponse(final int httpStatusCode, final RepositoryErrorResponseModel error) {
        super(httpStatusCode, error);
    }

    public RetrieveRepositoryContributorsResultResponse(final int httpStatusCode, final List<RepositoryErrorResponseModel> errors) {
        super(httpStatusCode, errors);
    }

    public RetrieveRepositoryContributorsResultResponse(final RetrieveRepositoryContributorsGridResponseModel response) {
        super(response);
    }
}
