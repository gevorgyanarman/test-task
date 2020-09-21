package com.test.task.service.respository.user.impl

import com.test.task.repository.repository.contributor.RepositoryContributorRepository
import com.test.task.helper.unit.repository.RepositoryCommonTestHelper
import com.test.task.helper.unit.repository.user.RepositoryContributorCommonTestHelper
import com.test.task.helper.unit.user.UserCommonTestHelper
import com.test.task.service.AbstractServiceUnitTest
import com.test.task.service.repository.RepositoryService
import com.test.task.service.repository.user.RepositoryContributorService
import com.test.task.service.user.UserService
import org.easymock.Mock
import org.junit.Before

abstract class AbstractRepositoryContributorServiceImplUnitTest : AbstractServiceUnitTest() {

    protected lateinit var repositoryContributorService: RepositoryContributorService

    @Mock
    protected lateinit var userService: UserService

    @Mock
    protected lateinit var repositoryService: RepositoryService

    @Mock
    protected lateinit var repositoryContributorRepository: RepositoryContributorRepository

    protected val userCommonTestHelper = UserCommonTestHelper()

    protected val repositoryCommonTestHelper = RepositoryCommonTestHelper()

    protected val commonTestHelper = RepositoryContributorCommonTestHelper()

    @Before
    fun prepare() {
        repositoryContributorService = RepositoryContributorServiceImpl(
                userService,
                repositoryService,
                repositoryContributorRepository
        )
    }
}