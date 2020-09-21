package com.test.task.commons.api.model.model.request.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class AbstractUuidAwareRequestModel extends AbstractRequestModel {

    @JsonProperty("uuid")
    private String uuid;

    public AbstractUuidAwareRequestModel() {
        super();
    }

    public AbstractUuidAwareRequestModel(final String uuid) {
        super();
        this.uuid = uuid;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractUuidAwareRequestModel)) {
            return false;
        }
        final AbstractUuidAwareRequestModel that = (AbstractUuidAwareRequestModel) o;
        return new EqualsBuilder()
                .append(uuid, that.uuid)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(uuid)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("uuid", uuid)
                .toString();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(final String uuid) {
        this.uuid = uuid;
    }
}
