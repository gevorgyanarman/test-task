package com.test.task.model.repository.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.task.commons.api.model.model.response.impl.AbstractUuidAwareResponseModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class RepositoryItemResponseModel extends AbstractUuidAwareResponseModel {

    @JsonProperty("name")
    private String name;

    @JsonProperty("fullName")
    private String fullName;

    public RepositoryItemResponseModel() {
        super();
    }

    public RepositoryItemResponseModel(final String uuId, final String name, final String fullName) {
        super(uuId);
        this.name = name;
        this.fullName = fullName;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RepositoryItemResponseModel)) {
            return false;
        }
        final RepositoryItemResponseModel that = (RepositoryItemResponseModel) o;
        return new EqualsBuilder()
                .append(name, that.name)
                .append(fullName, that.fullName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(name)
                .append(fullName)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("name", name)
                .append("fullName", fullName)
                .toString();
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }
}
