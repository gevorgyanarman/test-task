package com.test.task.service.repository.dto

import com.test.task.helper.unit.repository.RepositoryCommonTestHelper
import com.test.task.service.AbstractTest
import org.junit.Test


class SearchRepositoryDtoTest : AbstractTest() {

    private val commonTestHelper = RepositoryCommonTestHelper()

    @Test
    fun `test with invalid arguments`() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { commonTestHelper.buildSearchRepositoryDto(query = null) }
        verifyAll()
    }
}