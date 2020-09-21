package com.test.task.commons.api.model.model.response;

import java.util.List;

public interface ResultResponseModel<R extends ResponseModel, E extends ErrorResponseModel> {
    List<E> errors();

    void errors(final List<E> errors);

    boolean success();

    void success(final boolean success);

    R response();
}
