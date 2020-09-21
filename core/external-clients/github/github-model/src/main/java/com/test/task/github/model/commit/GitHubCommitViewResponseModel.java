package com.test.task.github.model.commit;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class GitHubCommitViewResponseModel {

    @JsonProperty("author")
    private GitHubCommitAuthorResponseModel author;

    public GitHubCommitViewResponseModel() {
        super();
    }

    public GitHubCommitViewResponseModel(final GitHubCommitAuthorResponseModel author) {
        this.author = author;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GitHubCommitViewResponseModel)) {
            return false;
        }
        final GitHubCommitViewResponseModel that = (GitHubCommitViewResponseModel) o;
        return new EqualsBuilder()
                .append(author, that.author)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(author)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("author", author)
                .toString();
    }

    public GitHubCommitAuthorResponseModel getAuthor() {
        return author;
    }

    public void setAuthor(final GitHubCommitAuthorResponseModel author) {
        this.author = author;
    }
}
