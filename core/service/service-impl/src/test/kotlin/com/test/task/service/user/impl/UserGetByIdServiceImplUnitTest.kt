package com.test.task.service.user.impl

import com.test.task.service.commons.EntityNotFoundForIdException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.easymock.EasyMock.expect
import org.junit.Test
import java.util.*


class UserGetByIdServiceImplUnitTest : AbstractUserServiceImplUnitTest() {

    @Test
    fun `test with invalid argument`() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { userService.getById(null) }
        verifyAll()
    }

    @Test
    fun `test when not found`() {
        val id = randomLong()
        resetAll()
        expect(userRepository.findById(id)).andReturn(Optional.empty())
        replayAll()
        assertThatThrownBy { userService.getById(id) }
                .isExactlyInstanceOf(EntityNotFoundForIdException::class.java)
        verifyAll()
    }

    @Test
    fun test() {
        val id = randomLong()
        val user = commonTestHelper.buildUser().apply { setId(id) }
        resetAll()
        expect(userRepository.findById(id)).andReturn(Optional.of(user))
        replayAll()
        userService.getById(id)
                .apply { assertThat(this).isEqualTo(user) }
        verifyAll()
    }
}