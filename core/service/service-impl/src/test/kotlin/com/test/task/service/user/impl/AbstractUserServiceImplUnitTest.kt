package com.test.task.service.user.impl

import com.test.task.repository.user.UserRepository
import com.test.task.helper.unit.user.UserCommonTestHelper
import com.test.task.service.AbstractServiceUnitTest
import com.test.task.service.user.UserService
import org.easymock.Mock
import org.junit.Before

abstract class AbstractUserServiceImplUnitTest : AbstractServiceUnitTest() {

    protected lateinit var userService: UserService

    @Mock
    protected lateinit var userRepository: UserRepository

    protected val commonTestHelper = UserCommonTestHelper()

    @Before
    fun prepare() {
        userService = UserServiceImpl(userRepository)
    }
}