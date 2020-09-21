package com.test.task.service.commit.impl

import com.test.task.repository.commit.CommitRepository
import com.test.task.helper.unit.commit.CommitCommonTestHelper
import com.test.task.helper.unit.repository.RepositoryCommonTestHelper
import com.test.task.helper.unit.user.UserCommonTestHelper
import com.test.task.service.AbstractServiceUnitTest
import com.test.task.service.commit.CommitService
import com.test.task.service.repository.RepositoryService
import com.test.task.service.user.UserService
import org.easymock.Mock
import org.junit.Before


abstract class AbstractCommitServiceImplUnitTest : AbstractServiceUnitTest() {

    protected lateinit var commitService: CommitService

    @Mock
    protected lateinit var commitRepository: CommitRepository

    @Mock
    protected lateinit var userService: UserService

    @Mock
    protected lateinit var repositoryService: RepositoryService

    protected val userCommonTestHelper = UserCommonTestHelper()
    protected val repositoryCommonTestHelper = RepositoryCommonTestHelper()
    protected val commonTestHelper = CommitCommonTestHelper()

    @Before
    fun prepare() {
        commitService = CommitServiceImpl(commitRepository, userService, repositoryService)
    }
}