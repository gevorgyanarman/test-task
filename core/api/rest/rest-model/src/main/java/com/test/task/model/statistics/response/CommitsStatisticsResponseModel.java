package com.test.task.model.statistics.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.task.commons.api.model.model.response.ResponseModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class CommitsStatisticsResponseModel implements ResponseModel {

    @JsonProperty("items")
    private List<CommitsStatisticsItemResponseModel> items;

    @JsonProperty("statisticsCommitsCount")
    private Integer statisticsCommitsCount;

    public CommitsStatisticsResponseModel() {
        super();
    }

    public CommitsStatisticsResponseModel(final List<CommitsStatisticsItemResponseModel> items, final Integer statisticsCommitsCount) {
        this.items = items;
        this.statisticsCommitsCount = statisticsCommitsCount;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommitsStatisticsResponseModel)) {
            return false;
        }
        final CommitsStatisticsResponseModel that = (CommitsStatisticsResponseModel) o;
        return new EqualsBuilder()
                .append(items, that.items)
                .append(statisticsCommitsCount, that.statisticsCommitsCount)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(items)
                .append(statisticsCommitsCount)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("items", items)
                .append("statisticsCommitsCount", statisticsCommitsCount)
                .toString();
    }

    public List<CommitsStatisticsItemResponseModel> getItems() {
        return items;
    }

    public void setItems(final List<CommitsStatisticsItemResponseModel> items) {
        this.items = items;
    }

    public Integer getStatisticsCommitsCount() {
        return statisticsCommitsCount;
    }

    public void setStatisticsCommitsCount(final Integer statisticsCommitsCount) {
        this.statisticsCommitsCount = statisticsCommitsCount;
    }
}
