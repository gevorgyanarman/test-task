package com.test.task.model.repository.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.task.commons.api.model.model.response.impl.AbstractUuidAwareResponseModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class UserResponseModel extends AbstractUuidAwareResponseModel {

    @JsonProperty("login")
    private String login;

    public UserResponseModel() {
        super();
    }

    public UserResponseModel(final String uuId, final String login) {
        super(uuId);
        this.login = login;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserResponseModel)) {
            return false;
        }
        final UserResponseModel that = (UserResponseModel) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(login, that.login)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(login)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("login", login)
                .toString();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }
}
