package com.test.task.service.repository.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.util.Assert;


public final class CreateRepositoryDto {

    private final String remoteId;
    private final String name;
    private final String fullName;

    public CreateRepositoryDto(final String remoteId, final String name, final String fullName) {
        Assert.hasText(remoteId, "The repository remote id should not be null or empty");
        Assert.hasText(name, "The repository name should not be null or empty");
        Assert.hasText(fullName, "The repository full name should not be null or empty");
        this.remoteId = remoteId;
        this.name = name;
        this.fullName = fullName;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreateRepositoryDto)) {
            return false;
        }
        final CreateRepositoryDto that = (CreateRepositoryDto) o;
        return new EqualsBuilder()
                .append(remoteId, that.remoteId)
                .append(name, that.name)
                .append(fullName, that.fullName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(remoteId)
                .append(name)
                .append(fullName)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("remoteId", remoteId)
                .append("name", name)
                .append("fullName", fullName)
                .toString();
    }

    public String getRemoteId() {
        return remoteId;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }
}
