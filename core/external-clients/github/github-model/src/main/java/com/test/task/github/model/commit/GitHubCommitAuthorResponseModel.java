package com.test.task.github.model.commit;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;


public class GitHubCommitAuthorResponseModel {

    @JsonProperty("name")
    private String name;

    @JsonProperty("date")
    private LocalDateTime dateTime;

    public GitHubCommitAuthorResponseModel() {
        super();
    }

    public GitHubCommitAuthorResponseModel(final String name, final LocalDateTime dateTime) {
        this.name = name;
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GitHubCommitAuthorResponseModel)) {
            return false;
        }
        final GitHubCommitAuthorResponseModel that = (GitHubCommitAuthorResponseModel) o;
        return new EqualsBuilder()
                .append(name, that.name)
                .append(dateTime, that.dateTime)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(name)
                .append(dateTime)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("dateTime", dateTime)
                .toString();
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(final LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

}
