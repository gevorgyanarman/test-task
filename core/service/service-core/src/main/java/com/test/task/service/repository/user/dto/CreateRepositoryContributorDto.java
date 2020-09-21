package com.test.task.service.repository.user.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.util.Assert;


public final class CreateRepositoryContributorDto {

    private final Long userId;
    private final Long repositoryId;

    public CreateRepositoryContributorDto(final Long userId, final Long repositoryId) {
        Assert.notNull(userId, "The user id should not be null");
        Assert.notNull(repositoryId, "The repository id should not be null");
        this.userId = userId;
        this.repositoryId = repositoryId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreateRepositoryContributorDto)) {
            return false;
        }
        final CreateRepositoryContributorDto that = (CreateRepositoryContributorDto) o;
        return new EqualsBuilder()
                .append(userId, that.userId)
                .append(repositoryId, that.repositoryId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(userId)
                .append(repositoryId)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userId", userId)
                .append("repositoryId", repositoryId)
                .toString();
    }

    public Long getUserId() {
        return userId;
    }

    public Long getRepositoryId() {
        return repositoryId;
    }
}

