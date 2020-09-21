package com.test.task.service.user.impl

import com.test.task.helper.unit.user.UserCommonTestHelper
import com.test.task.service.AbstractServiceUnitTest
import com.test.task.service.user.UserCompoundActionService
import com.test.task.service.user.UserService
import org.assertj.core.api.Assertions.assertThat
import org.easymock.EasyMock.expect
import org.easymock.Mock
import org.junit.Before
import org.junit.Test
import java.util.*


class UserCompoundActionServiceImplUnitTest : AbstractServiceUnitTest() {

    private lateinit var userCompoundActionService: UserCompoundActionService

    @Mock
    private lateinit var userService: UserService

    private val commonTestHelper = UserCommonTestHelper()

    @Before
    fun prepare() {
        userCompoundActionService = UserCompoundActionServiceImpl(userService)
    }

    @Test
    fun `test with invalid argument`() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { userCompoundActionService.createOrUpdate(null) }
        verifyAll()
    }

    @Test
    fun `test when not found`() {
        val dto = commonTestHelper.buildCreateOrUpdateUserDto()
        val createUserDto = commonTestHelper.buildCreateUserDto(remoteId = dto.remoteId, login = dto.login)
        val user = commonTestHelper.buildUser()
        resetAll()
        expect(userService.findByRemoteId(dto.remoteId)).andReturn(Optional.empty())
        expect(userService.create(createUserDto)).andReturn(user)
        replayAll()
        userCompoundActionService.createOrUpdate(dto)
                .apply { assertThat(this).isEqualTo(user) }
        verifyAll()
    }

    @Test
    fun `test when found`() {
        val dto = commonTestHelper.buildCreateOrUpdateUserDto()
        val userId = randomLong()
        val user = commonTestHelper.buildUser().apply { this.id = userId }
        val updateUserDto = commonTestHelper.buildUpdateUserDto(id = user.id, login = dto.login)
        val updatedUser = commonTestHelper.buildUser()
        resetAll()
        expect(userService.findByRemoteId(dto.remoteId)).andReturn(Optional.of(user))
        expect(userService.update(updateUserDto)).andReturn(updatedUser)
        replayAll()
        userCompoundActionService.createOrUpdate(dto)
                .apply { assertThat(this).isEqualTo(updatedUser) }
        verifyAll()
    }
}