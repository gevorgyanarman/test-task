package com.test.task.service.commit.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.util.Assert;

import java.time.LocalDateTime;


public final class CreateCommitDto {

    private final Long userId;
    private final Long repositoryId;
    private final String sha;
    private final LocalDateTime remoteCreationDate;

    public CreateCommitDto(final Long userId, final Long repositoryId, final String sha, final LocalDateTime remoteCreationDate) {
        Assert.notNull(userId, "The user id should not be null");
        Assert.notNull(repositoryId, "The repository id should not be null");
        Assert.hasText(sha, "The commit sha should not be null or empty");
        Assert.notNull(remoteCreationDate, "The commit remote creation date should not be null");
        this.userId = userId;
        this.repositoryId = repositoryId;
        this.sha = sha;
        this.remoteCreationDate = remoteCreationDate;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreateCommitDto)) {
            return false;
        }
        final CreateCommitDto that = (CreateCommitDto) o;
        return new EqualsBuilder()
                .append(userId, that.userId)
                .append(repositoryId, that.repositoryId)
                .append(sha, that.sha)
                .append(remoteCreationDate, that.remoteCreationDate)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(userId)
                .append(repositoryId)
                .append(sha)
                .append(remoteCreationDate)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userId", userId)
                .append("repositoryId", repositoryId)
                .append("sha", sha)
                .append("remoteCreationDate", remoteCreationDate)
                .toString();
    }

    public Long getUserId() {
        return userId;
    }

    public Long getRepositoryId() {
        return repositoryId;
    }

    public String getSha() {
        return sha;
    }

    public LocalDateTime getRemoteCreationDate() {
        return remoteCreationDate;
    }
}
