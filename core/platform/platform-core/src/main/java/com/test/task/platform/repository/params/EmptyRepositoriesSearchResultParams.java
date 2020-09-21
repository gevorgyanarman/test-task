package com.test.task.platform.repository.params;

import java.util.Collections;
import java.util.List;

class EmptyRepositoriesSearchResultParams<T> implements RepositoriesSearchResultParams<T> {

    @Override
    public long totalCount() {
        return 0;
    }

    @Override
    public List<T> items() {
        return Collections.emptyList();
    }
}
