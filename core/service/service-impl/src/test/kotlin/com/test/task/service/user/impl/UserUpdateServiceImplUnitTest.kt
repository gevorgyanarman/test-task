package com.test.task.service.user.impl

import com.test.task.domain.user.User
import org.assertj.core.api.Assertions.assertThat
import org.easymock.EasyMock.*
import org.junit.Test
import java.util.*


class UserUpdateServiceImplUnitTest : AbstractUserServiceImplUnitTest() {

    @Test
    fun `test with illegal argument`() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { userService.update(null) }
        verifyAll()
    }

    @Test
    fun test() {
        val dto = commonTestHelper.buildUpdateUserDto()
        val user = commonTestHelper.buildUser().apply { this.id = dto.id }
        resetAll()
        expect(userRepository.findById(dto.id)).andReturn(Optional.of(user))
        expect(userRepository.save(isA(User::class.java))).andAnswer { getCurrentArguments()[0] as User }
        replayAll()
        userService.update(dto)
                .apply { assertThat(this.id).isEqualTo(id) }
                .apply { assertThat(this.remoteId).isEqualTo(user.remoteId) }
                .apply { assertThat(this.login).isEqualTo(dto.login) }
        verifyAll()
    }
}