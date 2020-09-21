package com.test.task.github.model.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class GitHubWeeklyHashModel {

    @JsonProperty("w")
    private Long startOfWeek;

    @JsonProperty("a")
    private Long additionsCount;

    @JsonProperty("d")
    private Long deletionsCount;

    @JsonProperty("c")
    private Long commitsCount;

    public GitHubWeeklyHashModel() {
        super();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GitHubWeeklyHashModel)) {
            return false;
        }
        final GitHubWeeklyHashModel that = (GitHubWeeklyHashModel) o;
        return new EqualsBuilder()
                .append(startOfWeek, that.startOfWeek)
                .append(additionsCount, that.additionsCount)
                .append(deletionsCount, that.deletionsCount)
                .append(commitsCount, that.commitsCount)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(startOfWeek)
                .append(additionsCount)
                .append(deletionsCount)
                .append(commitsCount)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("startOfWeek", startOfWeek)
                .append("additionsCount", additionsCount)
                .append("deletionsCount", deletionsCount)
                .append("commitsCount", commitsCount)
                .toString();
    }

    public Long getStartOfWeek() {
        return startOfWeek;
    }

    public void setStartOfWeek(final Long startOfWeek) {
        this.startOfWeek = startOfWeek;
    }

    public Long getAdditionsCount() {
        return additionsCount;
    }

    public void setAdditionsCount(final Long additionsCount) {
        this.additionsCount = additionsCount;
    }

    public Long getDeletionsCount() {
        return deletionsCount;
    }

    public void setDeletionsCount(final Long deletionsCount) {
        this.deletionsCount = deletionsCount;
    }

    public Long getCommitsCount() {
        return commitsCount;
    }

    public void setCommitsCount(final Long commitsCount) {
        this.commitsCount = commitsCount;
    }
}
