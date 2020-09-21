package com.test.task.service.user.dto

import com.test.task.helper.unit.user.UserCommonTestHelper
import com.test.task.service.AbstractTest
import org.junit.Test


class UpdateUserDtoTest : AbstractTest() {

    private val commonTestHelper = UserCommonTestHelper()

    @Test
    fun `test with illegal arguments`() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { commonTestHelper.buildUpdateUserDto(id = null) }
        assertIllegalArgumentException { commonTestHelper.buildUpdateUserDto(login = null) }
        assertIllegalArgumentException { commonTestHelper.buildUpdateUserDto(login = emptyString()) }
        verifyAll()
    }

}