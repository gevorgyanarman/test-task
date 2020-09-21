package com.test.task.service.user.impl

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class UserFindByRemoteIdServiceIntegrationTest : AbstractUserServiceIntegrationTest() {

    @Test
    fun `test when not found`() {
        userService.findByRemoteId(uuId())
                .apply { assertThat(this).isNotPresent }
    }

    @Test
    fun test() {
        val user = integrationTestHelper.persistUser().apply { flushAndClear() }
        userService.findByRemoteId(user.remoteId)
                .apply { assertThat(this).isPresent }.get()
                .apply { assertThat(this).isEqualTo(user) }
    }
}