package com.test.task.model.repository.request;

import com.test.task.commons.api.model.model.request.ValidatableRequest;
import com.test.task.commons.api.model.model.request.impl.AbstractRequestModel;
import com.test.task.model.repository.error.RepositoryErrorResponseModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Collections;
import java.util.List;


public class RepositorySearchRequest extends AbstractRequestModel implements ValidatableRequest<RepositoryErrorResponseModel> {

    private final String query;

    public RepositorySearchRequest(final String query) {
        this.query = query;
    }

    @Override
    public List<RepositoryErrorResponseModel> validate() {
        if (StringUtils.isEmpty(query)) {
            return Collections.singletonList(RepositoryErrorResponseModel.REPOSITORY_NOT_FOUND);
        }
        return Collections.emptyList();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RepositorySearchRequest)) {
            return false;
        }
        final RepositorySearchRequest that = (RepositorySearchRequest) o;
        return new EqualsBuilder()
                .append(query, that.query)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(query)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("query", query)
                .toString();
    }

    public String getQuery() {
        return query;
    }
}
