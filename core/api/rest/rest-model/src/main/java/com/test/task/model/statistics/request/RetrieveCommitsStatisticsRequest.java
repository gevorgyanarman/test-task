package com.test.task.model.statistics.request;

import com.test.task.commons.api.model.model.request.impl.AbstractRequestModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class RetrieveCommitsStatisticsRequest extends AbstractRequestModel {

    private String repositoryUuId;

    public RetrieveCommitsStatisticsRequest() {
        super();
    }

    public RetrieveCommitsStatisticsRequest(final String repositoryUuId) {
        this.repositoryUuId = repositoryUuId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RetrieveCommitsStatisticsRequest)) {
            return false;
        }
        final RetrieveCommitsStatisticsRequest that = (RetrieveCommitsStatisticsRequest) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(repositoryUuId, that.repositoryUuId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(repositoryUuId)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
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
