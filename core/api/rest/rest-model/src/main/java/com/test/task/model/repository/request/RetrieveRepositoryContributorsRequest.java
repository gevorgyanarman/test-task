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


public class RetrieveRepositoryContributorsRequest extends AbstractRequestModel implements ValidatableRequest<RepositoryErrorResponseModel> {

    private String repositoryUuId;

    public RetrieveRepositoryContributorsRequest(final String repositoryUuId) {
        this.repositoryUuId = repositoryUuId;
    }

    @Override
    public List<RepositoryErrorResponseModel> validate() {
        if (StringUtils.isEmpty(repositoryUuId)) {
            return Collections.singletonList(RepositoryErrorResponseModel.MISSING_REPOSITORY_UUID);
        }
        return Collections.emptyList();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RetrieveRepositoryContributorsRequest)) {
            return false;
        }
        final RetrieveRepositoryContributorsRequest that = (RetrieveRepositoryContributorsRequest) o;
        return new EqualsBuilder()
                .append(repositoryUuId, that.repositoryUuId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(repositoryUuId)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("repositoryUuId", repositoryUuId)
                .toString();
    }

    public String getRepositoryUuId() {
        return repositoryUuId;
    }

    public void setRepositoryUuId(final String repositoryUuId) {
        this.repositoryUuId = repositoryUuId;
    }
}
