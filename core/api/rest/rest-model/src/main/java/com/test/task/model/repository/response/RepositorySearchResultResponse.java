package com.test.task.model.repository.response;

import com.test.task.commons.api.model.model.response.ErrorResponseModel;
import com.test.task.commons.api.model.model.response.impl.AbstractResultResponseModel;

import java.util.List;

public class RepositorySearchResultResponse extends AbstractResultResponseModel<RepositoriesGridResponseModel, ErrorResponseModel> {

    public RepositorySearchResultResponse() {
        super();
    }

    public RepositorySearchResultResponse(final int httpStatusCode, final ErrorResponseModel error) {
        super(httpStatusCode, error);
    }

    public RepositorySearchResultResponse(final int httpStatusCode, final List<ErrorResponseModel> errors) {
        super(httpStatusCode, errors);
    }

    public RepositorySearchResultResponse(final RepositoriesGridResponseModel response) {
        super(response);
    }
}
