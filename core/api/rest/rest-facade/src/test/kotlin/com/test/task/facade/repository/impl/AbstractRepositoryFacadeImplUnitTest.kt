package com.test.task.facade.repository.impl

import com.test.task.facade.AbstractFacadeUnitTest
import com.test.task.facade.repository.RepositoryFacade
import com.test.task.facade.user.model.UserModelBuilder
import com.test.task.helper.unit.repository.RepositoryCommonTestHelper
import com.test.task.helper.unit.user.UserCommonTestHelper
import com.test.task.platform.repository.RepositoryServiceFacade
import com.test.task.rest.test.helper.respository.RepositoryRestTestHelper
import com.test.task.rest.test.helper.user.UserRestTetHelper
import com.test.task.service.repository.RepositoryService
import org.easymock.Mock
import org.junit.Before

abstract class AbstractRepositoryFacadeImplUnitTest : AbstractFacadeUnitTest() {

    protected lateinit var repositoryFacade: RepositoryFacade

    @Mock
    protected lateinit var repositoryServiceFacade: RepositoryServiceFacade

    @Mock
    protected lateinit var repositoryService: RepositoryService

    @Mock
    internal lateinit var repositoryItemModelBuilder: RepositoryItemModelBuilder

    @Mock
    protected lateinit var userModelBuilder: UserModelBuilder

    protected val restTestHelper = RepositoryRestTestHelper()

    protected val userRestTetHelper = UserRestTetHelper()

    protected val commonTestHelper = RepositoryCommonTestHelper()

    protected val userCommonTestHelper = UserCommonTestHelper()

    @Before
    fun prepare() {
        repositoryFacade = RepositoryFacadeImpl(
                repositoryServiceFacade,
                repositoryService,
                repositoryItemModelBuilder,
                userModelBuilder
        )
    }
}