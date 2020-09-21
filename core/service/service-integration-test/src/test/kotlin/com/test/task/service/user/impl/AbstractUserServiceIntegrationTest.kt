package com.test.task.service.user.impl

import com.test.task.helper.interation.user.UserIntegrationTestHelper
import com.test.task.service.AbstractServiceIntegrationTest
import com.test.task.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired

abstract class AbstractUserServiceIntegrationTest : AbstractServiceIntegrationTest() {

    @Autowired
    protected lateinit var userService: UserService

    @Autowired
    protected lateinit var integrationTestHelper: UserIntegrationTestHelper
}