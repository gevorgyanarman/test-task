package com.test.task.commons.api.model.model.response.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.task.commons.api.model.model.response.GridResponseModel;
import com.test.task.commons.api.model.model.response.ResponseModel;

import java.util.List;


public class AbstractGridResponseModel<T extends ResponseModel> implements GridResponseModel<T> {

    @JsonProperty("totalCount")
    private long totalCount;

    @JsonProperty("grid")
    private List<T> items;

    protected AbstractGridResponseModel() {
        super();
    }

    public AbstractGridResponseModel(final long totalCount, final List<T> items) {
        this.totalCount = totalCount;
        this.items = items;
    }

    @Override
    public long totalCount() {
        return totalCount;
    }

    @Override
    public List<T> items() {
        return items;
    }
}
