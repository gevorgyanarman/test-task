package com.test.task.service.user.impl

import com.test.task.domain.user.User
import org.assertj.core.api.Assertions.assertThat
import org.easymock.EasyMock.*
import org.junit.Test

class UserCreateServiceImplUnitTest : AbstractUserServiceImplUnitTest() {

    @Test
    fun `test with invalid argument`() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { userService.create(null) }
        verifyAll()
    }

    @Test
    fun test() {
        val dto = commonTestHelper.buildCreateUserDto()
        resetAll()
        expect(userRepository.save(isA(User::class.java))).andAnswer { getCurrentArguments()[0] as User }
        replayAll()
        userService.create(dto)
                .apply { assertThat(this.login).isEqualTo(dto.login) }
                .apply { assertThat(this.remoteId).isEqualTo(dto.remoteId) }
        verifyAll()
    }
}