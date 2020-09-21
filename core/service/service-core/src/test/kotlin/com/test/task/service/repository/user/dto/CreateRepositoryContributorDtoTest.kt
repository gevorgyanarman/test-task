package com.test.task.service.repository.user.dto

import com.test.task.helper.unit.repository.user.RepositoryContributorCommonTestHelper
import com.test.task.service.AbstractTest
import org.junit.Test


class CreateRepositoryContributorDtoTest : AbstractTest() {

    private val commonTestHelper = RepositoryContributorCommonTestHelper()

    @Test
    fun `test with invalid arguments`() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { commonTestHelper.buildCreateRepositoryContributorDto(userId = null) }
        assertIllegalArgumentException { commonTestHelper.buildCreateRepositoryContributorDto(userId = null) }
        verifyAll()
    }
}