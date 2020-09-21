package com.test.task.service.user.impl

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class UserUpdateServiceIntegrationTest : AbstractUserServiceIntegrationTest() {

    @Test
    fun test() {
        val user = integrationTestHelper.persistUser()
        flushAndClear()
        val dto = integrationTestHelper.buildUpdateUserDto(id = user.id)
        userService.update(dto).apply { flushAndClear() }
        userService.getById(user.id)
                .apply { assertThat(this.remoteId).isEqualTo(user.remoteId) }
                .apply { assertThat(this.login).isEqualTo(dto.login) }
    }
}