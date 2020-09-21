package com.test.task.platform.repository.impl

import com.test.task.helper.unit.repository.RepositoryCommonTestHelper
import com.test.task.helper.unit.repository.user.RepositoryContributorCommonTestHelper
import com.test.task.helper.unit.user.UserCommonTestHelper
import com.test.task.platform.AbstractPlatformUnitTest
import com.test.task.platform.repository.RepositoryServiceFacade
import com.test.task.rest.test.helper.external.client.ExternalClientRestTestHelper
import com.test.task.service.repository.RepositoryCompoundActionService
import com.test.task.service.repository.RepositoryService
import com.test.task.service.repository.user.RepositoryContributorService
import com.test.task.service.user.UserCompoundActionService
import org.easymock.Mock
import org.junit.Before


abstract class AbstractRepositoryServiceFacadeImplUnitTest : AbstractPlatformUnitTest() {

    protected lateinit var repositoryServiceFacade: RepositoryServiceFacade

    @Mock
    protected lateinit var repositoryService: RepositoryService

    @Mock
    protected lateinit var repositoryCompoundActionService: RepositoryCompoundActionService

    @Mock
    internal lateinit var remoteRepositoryService: RemoteRepositoryService

    @Mock
    protected lateinit var userCompoundActionService: UserCompoundActionService

    @Mock
    protected lateinit var repositoryContributorService: RepositoryContributorService

    protected val repositoryCommonTestHelper = RepositoryCommonTestHelper()

    protected val repositoryContributorCommonTestHelper = RepositoryContributorCommonTestHelper()

    protected val userCommonTestHelper = UserCommonTestHelper()

    protected val externalClientRestTestHelper = ExternalClientRestTestHelper()

    @Before
    fun prepare() {
        repositoryServiceFacade = RepositoryServiceFacadeImpl(
                repositoryService,
                repositoryCompoundActionService,
                remoteRepositoryService,
                userCompoundActionService,
                repositoryContributorService
        )
    }
}