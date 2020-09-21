package com.test.task.commons.api.model.model.response.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.task.commons.api.model.model.response.ResponseModel;


public class AbstractUuidAwareResponseModel implements ResponseModel {

    @JsonProperty("uuId")
    private String uuId;

    protected AbstractUuidAwareResponseModel() {
    }

    public AbstractUuidAwareResponseModel(final String uuId) {
        this.uuId = uuId;
    }

    public String getUuId() {
        return uuId;
    }

    public void setUuId(final String uuId) {
        this.uuId = uuId;
    }
}
