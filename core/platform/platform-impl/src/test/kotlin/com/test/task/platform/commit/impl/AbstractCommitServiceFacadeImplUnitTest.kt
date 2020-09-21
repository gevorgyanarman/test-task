package com.test.task.platform.commit.impl

import com.test.task.helper.unit.commit.CommitCommonTestHelper
import com.test.task.helper.unit.repository.RepositoryCommonTestHelper
import com.test.task.helper.unit.user.UserCommonTestHelper
import com.test.task.platform.AbstractPlatformUnitTest
import com.test.task.platform.commit.CommitServiceFacade
import com.test.task.rest.test.helper.external.client.ExternalClientRestTestHelper
import com.test.task.service.commit.CommitService
import com.test.task.service.repository.RepositoryService
import com.test.task.service.user.UserCompoundActionService
import org.easymock.Mock
import org.junit.Before

abstract class AbstractCommitServiceFacadeImplUnitTest : AbstractPlatformUnitTest() {

    protected lateinit var commitServiceFacade: CommitServiceFacade

    @Mock
    protected lateinit var repositoryService: RepositoryService

    @Mock
    protected lateinit var userCompoundActionService: UserCompoundActionService

    @Mock
    protected lateinit var commitService: CommitService

    @Mock
    internal lateinit var remoteCommitsRetrievalService: RemoteCommitsRetrievalService

    protected val externalClientRestTestHelper = ExternalClientRestTestHelper()

    protected val commitCommonTestHelper = CommitCommonTestHelper()

    protected val repositoryCommonTestHelper = RepositoryCommonTestHelper()

    protected val userCommonTestHelper = UserCommonTestHelper()

    @Before
    fun prepare() {
        commitServiceFacade = CommitServiceFacadeImpl(
                repositoryService,
                userCompoundActionService,
                commitService,
                remoteCommitsRetrievalService
        )
    }
}