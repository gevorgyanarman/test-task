package com.test.task.model.repository.response;

import com.test.task.commons.api.model.model.response.impl.AbstractGridResponseModel;

import java.util.List;

public class RepositoriesGridResponseModel extends AbstractGridResponseModel<RepositoryItemResponseModel> {

    public RepositoriesGridResponseModel() {
        super();
    }

    public RepositoriesGridResponseModel(final long totalCount, final List<RepositoryItemResponseModel> items) {
        super(totalCount, items);
    }
}
