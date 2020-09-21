package com.test.task.facade.user.model.impl;

import com.test.task.facade.user.model.UserModelBuilder;
import com.test.task.domain.user.User;
import com.test.task.model.repository.response.UserResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
class UserModelBuilderImpl implements UserModelBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserModelBuilderImpl.class);

    UserModelBuilderImpl() {
        LOGGER.debug("Initializing - {}", getClass().getCanonicalName());
    }

    @Override
    public UserResponseModel build(final User user) {
        LOGGER.debug("Building repository user response model for the provided user - {}", user);
        Assert.notNull(user, "The user should not be null");
        final UserResponseModel responseModel = new UserResponseModel(
                user.getUuId(),
                user.getLogin()
        );
        LOGGER.debug("Building repository user response model for the provided user - {}", user);
        return responseModel;
    }
}
