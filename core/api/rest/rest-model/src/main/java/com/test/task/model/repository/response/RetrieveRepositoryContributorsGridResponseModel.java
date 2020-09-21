package com.test.task.model.repository.response;

import com.test.task.commons.api.model.model.response.impl.AbstractGridResponseModel;

import java.util.List;


public class RetrieveRepositoryContributorsGridResponseModel extends AbstractGridResponseModel<UserResponseModel> {

    public RetrieveRepositoryContributorsGridResponseModel() {
        super();
    }

    public RetrieveRepositoryContributorsGridResponseModel(final long totalCount, final List<UserResponseModel> items) {
        super(totalCount, items);
    }
}
