package com.test.task.facade.user.model;

import com.test.task.domain.user.User;
import com.test.task.model.repository.response.UserResponseModel;

public interface UserModelBuilder {

    /**
     * Build user response model for the provided user entity
     *
     * @param user the user entity
     * @return the user response model
     */
    UserResponseModel build(final User user);
}
