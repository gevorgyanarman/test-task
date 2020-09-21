package com.test.task.service.repository.dto;

import com.test.task.service.commons.AbstractPaginationAwareDto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.util.Assert;


public class SearchRepositoryDto extends AbstractPaginationAwareDto {

    private final String query;

    public SearchRepositoryDto(final int size, final String query) {
        super(size);
        Assert.notNull(query, "The query should not be null");
        this.query = query;
    }

    public SearchRepositoryDto(final int page, final int size, final String query) {
        super(page, size);
        Assert.notNull(query, "The query should not be null");
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SearchRepositoryDto)) {
            return false;
        }
        final SearchRepositoryDto that = (SearchRepositoryDto) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(query, that.query)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(query)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("query", query)
                .toString();
    }
}
