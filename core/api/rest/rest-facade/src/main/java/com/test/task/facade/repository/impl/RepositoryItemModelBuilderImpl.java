package com.test.task.facade.repository.impl;

import com.test.task.domain.respository.Repository;
import com.test.task.model.repository.response.RepositoryItemResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
class RepositoryItemModelBuilderImpl implements RepositoryItemModelBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryItemModelBuilderImpl.class);

    RepositoryItemModelBuilderImpl() {
        LOGGER.debug("Initializing - {}", getClass().getCanonicalName());
    }

    @Override
    public RepositoryItemResponseModel build(final Repository repository) {
        LOGGER.debug("Building repository item response model for the provided repository - {}", repository);
        Assert.notNull(repository, "The repository should not be null");
        final RepositoryItemResponseModel responseModel = new RepositoryItemResponseModel(
                repository.getUuId(),
                repository.getName(),
                repository.getFullName()
        );
        LOGGER.debug(
                "Successfully built repository item response model for the provided repository - {}, result - {}",
                repository,
                responseModel
        );
        return responseModel;
    }
}
