package com.test.task.github.model.commit;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Optional;


public class GitHubCommitsItemResponseModel {

    @JsonProperty("sha")
    private String sha;

    @JsonProperty("commit")
    private GitHubCommitViewResponseModel commit;

    @JsonProperty("author")
    private GitHubAuthorResponseModel author;

    public GitHubCommitsItemResponseModel() {
        super();
    }

    public GitHubCommitsItemResponseModel(final String sha,
                                          final GitHubCommitViewResponseModel commit,
                                          final GitHubAuthorResponseModel author) {
        this.sha = sha;
        this.commit = commit;
        this.author = author;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GitHubCommitsItemResponseModel)) {
            return false;
        }
        final GitHubCommitsItemResponseModel that = (GitHubCommitsItemResponseModel) o;
        return new EqualsBuilder()
                .append(sha, that.sha)
                .append(commit, that.commit)
                .append(author, that.author)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(sha)
                .append(commit)
                .append(author)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("sha", sha)
                .append("commit", commit)
                .append("author", author)
                .toString();
    }

    public String getSha() {
        return sha;
    }

    public void setSha(final String sha) {
        this.sha = sha;
    }

    public GitHubCommitViewResponseModel getCommit() {
        return commit;
    }

    public void setCommit(final GitHubCommitViewResponseModel commit) {
        this.commit = commit;
    }

    public Optional<GitHubAuthorResponseModel> getAuthor() {
        return Optional.ofNullable(author);
    }

    public void setAuthor(final GitHubAuthorResponseModel author) {
        this.author = author;
    }
}
