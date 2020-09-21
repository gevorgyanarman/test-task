package com.test.task.github.model.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;


public class GitHubContributorsItemStatisticsViewModel {

    @JsonProperty("total")
    private Long totalCount;

    @JsonProperty("weeks")
    private List<GitHubWeeklyHashModel> weeklyHashModels;

    @JsonProperty("author")
    private GitHubContributorUserViewModel user;

    public GitHubContributorsItemStatisticsViewModel() {
        super();
    }

    public GitHubContributorsItemStatisticsViewModel(final Long totalCount,
                                                     final List<GitHubWeeklyHashModel> weeklyHashModels,
                                                     final GitHubContributorUserViewModel user) {
        this.totalCount = totalCount;
        this.weeklyHashModels = weeklyHashModels;
        this.user = user;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GitHubContributorsItemStatisticsViewModel)) {
            return false;
        }
        final GitHubContributorsItemStatisticsViewModel that = (GitHubContributorsItemStatisticsViewModel) o;
        return new EqualsBuilder()
                .append(totalCount, that.totalCount)
                .append(weeklyHashModels, that.weeklyHashModels)
                .append(user, that.user)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(totalCount)
                .append(weeklyHashModels)
                .append(user)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("totalCount", totalCount)
                .append("weeklyHashModels", weeklyHashModels)
                .append("user", user)
                .toString();
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(final Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<GitHubWeeklyHashModel> getWeeklyHashModels() {
        return weeklyHashModels;
    }

    public void setWeeklyHashModels(final List<GitHubWeeklyHashModel> weeklyHashModels) {
        this.weeklyHashModels = weeklyHashModels;
    }

    public GitHubContributorUserViewModel getUser() {
        return user;
    }

    public void setUser(final GitHubContributorUserViewModel user) {
        this.user = user;
    }
}
