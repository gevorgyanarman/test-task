package com.test.task.service.repository.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.util.Assert;


public final class UpdateRepositoryDto {

    private final Long id;
    private final String name;
    private final String fullName;

    public UpdateRepositoryDto(final Long id, final String name, final String fullName) {
        Assert.notNull(id, "The repository id should not be null");
        Assert.hasText(name, "The repository name should not be null or empty");
        Assert.hasText(fullName, "The repository full name should not be null or empty");
        this.id = id;
        this.name = name;
        this.fullName = fullName;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UpdateRepositoryDto)) {
            return false;
        }
        final UpdateRepositoryDto that = (UpdateRepositoryDto) o;
        return new EqualsBuilder()
                .append(id, that.id)
                .append(name, that.name)
                .append(fullName, that.fullName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(name)
                .append(fullName)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("fullName", fullName)
                .toString();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }
}
