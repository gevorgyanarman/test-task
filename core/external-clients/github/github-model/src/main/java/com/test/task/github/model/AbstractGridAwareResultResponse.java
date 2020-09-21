package com.test.task.github.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class AbstractGridAwareResultResponse<T> {

    @JsonProperty("total_count")
    private Long totalCount;

    @JsonProperty("incomplete_results")
    private Boolean incompleteResults;

    @JsonProperty("items")
    private List<T> items;

    public AbstractGridAwareResultResponse() {
        super();
    }

    public AbstractGridAwareResultResponse(final Long totalCount, final Boolean incompleteResults, final List<T> items) {
        this.totalCount = totalCount;
        this.incompleteResults = incompleteResults;
        this.items = items;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractGridAwareResultResponse)) {
            return false;
        }
        final AbstractGridAwareResultResponse<?> that = (AbstractGridAwareResultResponse<?>) o;
        return new EqualsBuilder()
                .append(totalCount, that.totalCount)
                .append(incompleteResults, that.incompleteResults)
                .append(items, that.items)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(totalCount)
                .append(incompleteResults)
                .append(items)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("totalCount", totalCount)
                .append("incompleteResults", incompleteResults)
                .append("items", items)
                .toString();
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public Boolean getIncompleteResults() {
        return incompleteResults;
    }

    public List<T> getItems() {
        return items;
    }
}
