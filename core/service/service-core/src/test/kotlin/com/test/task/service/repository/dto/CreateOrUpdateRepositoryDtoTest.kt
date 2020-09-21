package com.test.task.service.repository.dto

import com.test.task.helper.unit.repository.RepositoryCommonTestHelper
import com.test.task.service.AbstractTest
import org.junit.Test


class CreateOrUpdateRepositoryDtoTest : AbstractTest() {

    private val commonTestHelper = RepositoryCommonTestHelper()

    @Test
    fun `test with invalid arguments`() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { commonTestHelper.buildCreateOrUpdateRepositoryDto(remoteId = null) }
        assertIllegalArgumentException { commonTestHelper.buildCreateOrUpdateRepositoryDto(remoteId = emptyString()) }
        assertIllegalArgumentException { commonTestHelper.buildCreateOrUpdateRepositoryDto(name = null) }
        assertIllegalArgumentException { commonTestHelper.buildCreateOrUpdateRepositoryDto(name = emptyString()) }
        assertIllegalArgumentException { commonTestHelper.buildCreateOrUpdateRepositoryDto(fullName = null) }
        assertIllegalArgumentException { commonTestHelper.buildCreateOrUpdateRepositoryDto(fullName = emptyString()) }
        verifyAll()
    }
}