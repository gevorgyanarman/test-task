package com.test.task.helper.unit.user

import com.test.task.domain.user.User
import com.test.task.helper.AbstractTestHelper
import com.test.task.service.user.dto.CreateOrUpdateUserDto
import com.test.task.service.user.dto.CreateUserDto
import com.test.task.service.user.dto.UpdateUserDto


open class UserCommonTestHelper : AbstractTestHelper() {

    fun buildCreateUserDto(remoteId: String? = uuId(),
                           login: String? = uuId()
    ): CreateUserDto = CreateUserDto(remoteId, login)

    fun buildUpdateUserDto(id: Long? = randomLong(),
                           login: String? = uuId()
    ): UpdateUserDto = UpdateUserDto(id, login)

    fun buildCreateOrUpdateUserDto(remoteId: String? = uuId(),
                                   login: String? = uuId()
    ): CreateOrUpdateUserDto = CreateOrUpdateUserDto(remoteId, login)

    fun buildUser(login: String? = uuId(),
                  remoteId: String? = uuId()
    ): User = User(login, remoteId)
}