package com.test.task.service.user.impl

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class UserGetByIdServiceIntegrationTest : AbstractUserServiceIntegrationTest() {

    @Test
    fun test() {
        val user = integrationTestHelper.persistUser().apply { flushAndClear() }
        userService.getById(user.id)
                .apply { assertThat(this).isEqualTo(user) }
    }
}