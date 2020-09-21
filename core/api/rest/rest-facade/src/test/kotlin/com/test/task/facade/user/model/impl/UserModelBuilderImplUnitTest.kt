package com.test.task.facade.user.model.impl

import com.test.task.facade.AbstractFacadeUnitTest
import com.test.task.facade.user.model.UserModelBuilder
import com.test.task.helper.unit.user.UserCommonTestHelper
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class UserModelBuilderImplUnitTest : AbstractFacadeUnitTest() {

    private lateinit var builder: UserModelBuilder

    private val userCommonTestHelper = UserCommonTestHelper()

    @Before
    fun prepare() {
        builder = UserModelBuilderImpl()
    }

    @Test
    fun `test with invalid argument`() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { builder.build(null) }
        verifyAll()
    }

    @Test
    fun test() {
        val user = userCommonTestHelper.buildUser()
        resetAll()
        replayAll()
        builder.build(user)
                .apply { assertThat(this.uuId).isEqualTo(user.uuId) }
                .apply { assertThat(this.login).isEqualTo(user.login) }
        verifyAll()
    }
}