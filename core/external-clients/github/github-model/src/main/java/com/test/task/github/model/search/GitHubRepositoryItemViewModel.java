package com.test.task.github.model.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.task.github.model.AbstractIdAwareResponseModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class GitHubRepositoryItemViewModel extends AbstractIdAwareResponseModel {

    @JsonProperty("name")
    private String name;

    @JsonProperty("full_name")
    private String fullName;

    public GitHubRepositoryItemViewModel() {
        super();
    }

    public GitHubRepositoryItemViewModel(final String id, final String name, final String fullName) {
        super(id);
        this.name = name;
        this.fullName = fullName;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GitHubRepositoryItemViewModel)) {
            return false;
        }
        final GitHubRepositoryItemViewModel that = (GitHubRepositoryItemViewModel) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(name, that.name)
                .append(fullName, that.fullName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(name)
                .append(fullName)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("fullName", fullName)
                .toString();
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }
}
