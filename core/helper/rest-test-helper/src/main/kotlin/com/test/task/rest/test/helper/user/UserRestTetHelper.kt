package com.test.task.rest.test.helper.user

import com.test.task.model.repository.response.UserResponseModel
import com.test.task.rest.test.helper.AbstractRestTestHelper

class UserRestTetHelper : AbstractRestTestHelper() {

    fun buildUserResponseModel(uuId: String? = uuId(),
                               login: String? = uuId()
    ): UserResponseModel = UserResponseModel(uuId, login)
}