package com.test.task.platform.repository.params;

import java.util.List;

public interface RepositoriesSearchResultParams<T> {

    long totalCount();

    List<T> items();

    static <T> RepositoriesSearchResultParams<T> empty() {
        return new EmptyRepositoriesSearchResultParams<>();
    }

    static <T> RepositoriesSearchResultParams<T> of(final long totalCount, final List<T> items) {
        return new RepositoriesSearchResultParamsImpl<>(totalCount, items);
    }
}
