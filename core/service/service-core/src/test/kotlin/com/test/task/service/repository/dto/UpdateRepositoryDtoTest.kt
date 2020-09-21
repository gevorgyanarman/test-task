package com.test.task.service.repository.dto

import com.test.task.helper.unit.repository.RepositoryCommonTestHelper
import com.test.task.service.AbstractTest
import org.junit.Test


class UpdateRepositoryDtoTest : AbstractTest() {

    private val commonTestHelper = RepositoryCommonTestHelper()

    @Test
    fun `test with illegal arguments`() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { commonTestHelper.buildUpdateRepositoryDto(id = null) }
        assertIllegalArgumentException { commonTestHelper.buildUpdateRepositoryDto(name = null) }
        assertIllegalArgumentException { commonTestHelper.buildUpdateRepositoryDto(name = emptyString()) }
        assertIllegalArgumentException { commonTestHelper.buildUpdateRepositoryDto(fullName = null) }
        assertIllegalArgumentException { commonTestHelper.buildUpdateRepositoryDto(fullName = emptyString()) }
        verifyAll()
    }

}