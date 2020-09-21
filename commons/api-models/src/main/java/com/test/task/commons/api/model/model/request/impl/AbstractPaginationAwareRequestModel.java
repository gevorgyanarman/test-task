package com.test.task.commons.api.model.model.request.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class AbstractPaginationAwareRequestModel extends AbstractRequestModel {

    @JsonProperty("page")
    private Integer page;

    @JsonProperty("size")
    private Integer size;

    public AbstractPaginationAwareRequestModel() {
        super();
    }

    public AbstractPaginationAwareRequestModel(final Integer page, final Integer size) {
        this.page = page;
        this.size = size;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractPaginationAwareRequestModel)) {
            return false;
        }
        final AbstractPaginationAwareRequestModel that = (AbstractPaginationAwareRequestModel) o;
        return new EqualsBuilder()
                .append(page, that.page)
                .append(size, that.size)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(page)
                .append(size)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("page", page)
                .append("size", size)
                .toString();
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(final Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(final Integer size) {
        this.size = size;
    }
}
