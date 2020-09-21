package com.test.task.github.model.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.task.github.model.AbstractIdAwareResponseModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class GitHubContributorUserViewModel extends AbstractIdAwareResponseModel {

    @JsonProperty("login")
    private String login;

    public GitHubContributorUserViewModel() {
        super();
    }

    public GitHubContributorUserViewModel(final String id, final String login) {
        super(id);
        this.login = login;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GitHubContributorUserViewModel)) {
            return false;
        }
        final GitHubContributorUserViewModel that = (GitHubContributorUserViewModel) o;
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
}
