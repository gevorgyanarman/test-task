package com.test.task.service.user.dto

import com.test.task.helper.unit.user.UserCommonTestHelper
import com.test.task.service.AbstractTest
import org.junit.Test

class CreateUserDtoTest : AbstractTest() {

    private val commonTestHelper = UserCommonTestHelper()

    @Test
    fun `test with invalid arguments`() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { commonTestHelper.buildCreateUserDto(remoteId = null) }
        assertIllegalArgumentException { commonTestHelper.buildCreateUserDto(remoteId = emptyString()) }
        assertIllegalArgumentException { commonTestHelper.buildCreateUserDto(login = null) }
        assertIllegalArgumentException { commonTestHelper.buildCreateUserDto(login = emptyString()) }
        verifyAll()
    }
}