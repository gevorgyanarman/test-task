package com.test.task.model.statistics.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.task.commons.api.model.model.response.ResponseModel;
import com.test.task.model.repository.response.UserResponseModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class CommitsStatisticsItemResponseModel implements ResponseModel {

    @JsonProperty("user")
    private UserResponseModel user;

    @JsonProperty("commitsCount")
    private Long commitsCount;

    public CommitsStatisticsItemResponseModel() {
        super();
    }

    public CommitsStatisticsItemResponseModel(final UserResponseModel user, final Long commitsCount) {
        this.user = user;
        this.commitsCount = commitsCount;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommitsStatisticsItemResponseModel)) {
            return false;
        }
        final CommitsStatisticsItemResponseModel that = (CommitsStatisticsItemResponseModel) o;
        return new EqualsBuilder()
                .append(user, that.user)
                .append(commitsCount, that.commitsCount)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(user)
                .append(commitsCount)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("user", user)
                .append("commitsCount", commitsCount)
                .toString();
    }

    public UserResponseModel getUser() {
        return user;
    }

    public void setUser(final UserResponseModel user) {
        this.user = user;
    }

    public Long getCommitsCount() {
        return commitsCount;
    }

    public void setCommitsCount(final Long commitsCount) {
        this.commitsCount = commitsCount;
    }
}
