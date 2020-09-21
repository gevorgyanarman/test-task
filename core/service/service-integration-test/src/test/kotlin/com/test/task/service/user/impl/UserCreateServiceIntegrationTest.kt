package com.test.task.service.user.impl

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test

class UserCreateServiceIntegrationTest : AbstractUserServiceIntegrationTest() {

    @Test
    fun test() {
        val dto = integrationTestHelper.buildCreateUserDto()
        userService.create(dto)
                .apply { flushAndClear() }
                .apply { assertThat(this.login).isEqualTo(dto.login) }
                .apply { assertThat(this.remoteId).isEqualTo(dto.remoteId) }
    }

    @Test
    fun `test multiple creation with same remote id`() {
        val dto = integrationTestHelper.buildCreateUserDto()
        userService.create(dto).apply { flushAndClear() }
        assertThatThrownBy { userService.create(dto).apply { flushAndClear() } }
    }
}