package com.test.task.service.user.dto

import com.test.task.helper.unit.user.UserCommonTestHelper
import com.test.task.service.AbstractTest
import org.junit.Test


class CreateOrUpdateUserDtoTest : AbstractTest() {

    private val commonTestHelper = UserCommonTestHelper()

    @Test
    fun `test with invalid arguments`() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { commonTestHelper.buildCreateOrUpdateUserDto(remoteId = null) }
        assertIllegalArgumentException { commonTestHelper.buildCreateOrUpdateUserDto(remoteId = emptyString()) }
        assertIllegalArgumentException { commonTestHelper.buildCreateOrUpdateUserDto(login = null) }
        assertIllegalArgumentException { commonTestHelper.buildCreateOrUpdateUserDto(login = emptyString()) }
        verifyAll()
    }
}