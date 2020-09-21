package com.test.task.service.user.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.util.Assert;

public final class UpdateUserDto {

    private final Long id;
    private final String login;

    public UpdateUserDto(final Long id, final String login) {
        Assert.notNull(id, "The user id should not be null");
        Assert.hasText(login, "The user login should not be null or empty");
        this.id = id;
        this.login = login;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UpdateUserDto)) {
            return false;
        }
        final UpdateUserDto that = (UpdateUserDto) o;
        return new EqualsBuilder()
                .append(id, that.id)
                .append(login, that.login)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(login)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("login", login)
                .toString();
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }
}
