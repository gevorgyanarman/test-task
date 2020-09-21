package com.test.task.model.repository.error;

import com.test.task.commons.api.model.model.response.ErrorResponseModel;

public enum RepositoryErrorResponseModel implements ErrorResponseModel {
    MISSING_REPOSITORY_UUID,
    REPOSITORY_NOT_FOUND
}
