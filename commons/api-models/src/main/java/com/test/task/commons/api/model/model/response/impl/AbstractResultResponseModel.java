package com.test.task.commons.api.model.model.response.impl;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.task.commons.api.model.model.response.ErrorResponseModel;
import com.test.task.commons.api.model.model.response.ResponseModel;
import com.test.task.commons.api.model.model.response.ResultResponseModel;

import java.util.Collections;
import java.util.List;


public abstract class AbstractResultResponseModel<T extends ResponseModel, E extends ErrorResponseModel> implements ResultResponseModel<T, E> {

    @JsonProperty("success")
    private boolean success;

    @JsonProperty("errors")
    private List<E> errors;

    @JsonProperty("response")
    private T response;

    @JsonIgnore
    private transient int httpStatusCode = 200;

    protected AbstractResultResponseModel() {
        super();
    }

    protected AbstractResultResponseModel(final int httpStatusCode, final E error) {
        this(httpStatusCode, Collections.singletonList(error));
    }

    protected AbstractResultResponseModel(final int httpStatusCode, final List<E> errors) {
        this.httpStatusCode = httpStatusCode;
        this.success = false;
        this.errors = errors;
        this.response = null;
    }

    protected AbstractResultResponseModel(final T response) {
        this.success = true;
        this.errors = Collections.emptyList();
        this.response = response;
    }

    protected AbstractResultResponseModel(final T response, final int httpStatusCode) {
        this(response);
        this.httpStatusCode = httpStatusCode;
    }

    @Override
    public List<E> errors() {
        return errors;
    }

    @Override
    public void errors(final List<E> errors) {
        this.errors = errors;
    }

    @Override
    public boolean success() {
        return success;
    }

    public void success(final boolean success) {
        this.success = success;
    }

    @Override
    public T response() {
        return response;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(final int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }
}
