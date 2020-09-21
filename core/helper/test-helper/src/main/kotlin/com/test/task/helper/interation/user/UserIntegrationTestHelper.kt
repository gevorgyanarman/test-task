package com.test.task.helper.interation.user

import com.test.task.domain.user.User
import com.test.task.helper.unit.user.UserCommonTestHelper
import com.test.task.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class UserIntegrationTestHelper @Autowired constructor(
        private val userService: UserService
) : UserCommonTestHelper() {

    fun persistUser(remoteId: String? = uuId(),
                    login: String? = uuId()
    ): User = buildCreateUserDto(remoteId, login).let { userService.create(it) }
}