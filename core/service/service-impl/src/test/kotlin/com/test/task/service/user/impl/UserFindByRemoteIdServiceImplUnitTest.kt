package com.test.task.service.user.impl

import org.assertj.core.api.Assertions.assertThat
import org.easymock.EasyMock.*
import org.junit.Test
import java.util.*


class UserFindByRemoteIdServiceImplUnitTest : AbstractUserServiceImplUnitTest() {

    @Test
    fun `test with invalid argument`() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { userService.findByRemoteId(null) }
        assertIllegalArgumentException { userService.findByRemoteId(emptyString()) }
        verifyAll()
    }

    @Test
    fun `test when not found`() {
        val remoteId = uuId()
        resetAll()
        expect(userRepository.findByRemoteId(remoteId)).andReturn(Optional.empty())
        replayAll()
        userService.findByRemoteId(remoteId)
                .apply { assertThat(this).isEmpty }
        verifyAll()
    }

    @Test
    fun test() {
        val remoteId = uuId()
        val user = commonTestHelper.buildUser()
        resetAll()
        expect(userRepository.findByRemoteId(remoteId)).andReturn(Optional.of(user))
        replayAll()
        userService.findByRemoteId(remoteId)
                .apply { assertThat(this).isPresent }
                .get()
                .apply { assertThat(this).isEqualTo(user) }
        verifyAll()
    }
}