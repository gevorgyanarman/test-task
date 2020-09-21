package com.test.task.facade.repository.impl;

import com.test.task.model.repository.response.RepositoryItemResponseModel;
import com.test.task.domain.respository.Repository;

interface RepositoryItemModelBuilder {
    /**
     * Build repository item response model for the provided repository entity
     *
     * @param repository the repository entity
     * @return the repository item response model
     */
    RepositoryItemResponseModel build(final Repository repository);
}
