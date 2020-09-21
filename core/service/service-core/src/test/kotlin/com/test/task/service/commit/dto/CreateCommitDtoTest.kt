package com.test.task.service.commit.dto

import com.test.task.helper.unit.commit.CommitCommonTestHelper
import com.test.task.service.AbstractTest
import org.junit.Test


class CreateCommitDtoTest : AbstractTest() {

    private val commonTestHelper = CommitCommonTestHelper()

    @Test
    fun test() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { commonTestHelper.buildCreateCommitDto(userId = null) }
        assertIllegalArgumentException { commonTestHelper.buildCreateCommitDto(repositoryId = null) }
        assertIllegalArgumentException { commonTestHelper.buildCreateCommitDto(sha = null) }
        assertIllegalArgumentException { commonTestHelper.buildCreateCommitDto(sha = emptyString()) }
        assertIllegalArgumentException { commonTestHelper.buildCreateCommitDto(remoteCreationDate = null) }
        verifyAll()
    }
}