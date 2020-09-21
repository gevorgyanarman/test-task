package com.test.task.platform.repository.params;

import java.util.List;

final class RepositoriesSearchResultParamsImpl<T> implements RepositoriesSearchResultParams<T> {

    private final long totalCount;
    private final List<T> items;

    RepositoriesSearchResultParamsImpl(final long totalCount, final List<T> items) {
        this.totalCount = totalCount;
        this.items = items;
    }

    @Override
    public long totalCount() {
        return totalCount;
    }

    @Override
    public List<T> items() {
        return items;
    }
}
