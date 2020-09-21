package com.test.task.service.repository.dto

import com.test.task.helper.unit.repository.RepositoryCommonTestHelper
import com.test.task.service.AbstractTest
import org.junit.Test


class CreateRepositoryDtoTest : AbstractTest() {

    private val commonTestHelper = RepositoryCommonTestHelper()

    @Test
    fun `test with invalid arguments`() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { commonTestHelper.buildCreateRepositoryDto(remoteId = null) }
        assertIllegalArgumentException { commonTestHelper.buildCreateRepositoryDto(remoteId = emptyString()) }
        assertIllegalArgumentException { commonTestHelper.buildCreateRepositoryDto(name = null) }
        assertIllegalArgumentException { commonTestHelper.buildCreateRepositoryDto(name = emptyString()) }
        assertIllegalArgumentException { commonTestHelper.buildCreateRepositoryDto(fullName = null) }
        assertIllegalArgumentException { commonTestHelper.buildCreateRepositoryDto(fullName = emptyString()) }
        verifyAll()
    }
}