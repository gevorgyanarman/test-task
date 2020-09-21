package com.test.task.service.user.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.util.Assert;


public final class CreateOrUpdateUserDto {

    private final String remoteId;
    private final String login;

    public CreateOrUpdateUserDto(final String remoteId, final String login) {
        Assert.hasText(remoteId, "The user remote id should not be null or empty");
        Assert.hasText(login, "The user login should not be null or empty");
        this.remoteId = remoteId;
        this.login = login;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreateOrUpdateUserDto)) {
            return false;
        }
        final CreateOrUpdateUserDto that = (CreateOrUpdateUserDto) o;
        return new EqualsBuilder()
                .append(remoteId, that.remoteId)
                .append(login, that.login)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(remoteId)
                .append(login)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("remoteId", remoteId)
                .append("login", login)
                .toString();
    }

    public String getRemoteId() {
        return remoteId;
    }

    public String getLogin() {
        return login;
    }
}
