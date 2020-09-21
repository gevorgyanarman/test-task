package com.test.task.github.model.commit;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class GitHubAuthorResponseModel {

    @JsonProperty("id")
    private String id;

    @JsonProperty("login")
    private String login;

    public GitHubAuthorResponseModel() {
        super();
    }

    public GitHubAuthorResponseModel(final String id, final String login) {
        this.id = id;
        this.login = login;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GitHubAuthorResponseModel)) {
            return false;
        }
        final GitHubAuthorResponseModel that = (GitHubAuthorResponseModel) o;
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

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }
}
