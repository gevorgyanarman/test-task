package com.test.task.commons.api.model.model.request;

import com.test.task.commons.api.model.model.response.ErrorResponseModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public interface ValidatableRequest<E extends ErrorResponseModel> {
    default List<E> validate() {
        return Collections.emptyList();
    }

    default List<E> initializeNew() {
        return new ArrayList<>();
    }
}
