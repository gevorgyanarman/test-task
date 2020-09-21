package com.test.task.facade.statistics.impl

import com.test.task.facade.AbstractFacadeUnitTest
import com.test.task.facade.user.model.UserModelBuilder
import com.test.task.facade.statistics.StatisticsFacade
import com.test.task.helper.unit.commit.CommitCommonTestHelper
import com.test.task.helper.unit.repository.RepositoryCommonTestHelper
import com.test.task.helper.unit.user.UserCommonTestHelper
import com.test.task.platform.commit.CommitServiceFacade
import com.test.task.rest.test.helper.statistics.StatisticsRestTestHelper
import com.test.task.rest.test.helper.user.UserRestTetHelper
import com.test.task.service.repository.RepositoryService
import org.easymock.Mock
import org.junit.Before

abstract class AbstractStatisticsFacadeImplUnitTest : AbstractFacadeUnitTest() {

    protected lateinit var statisticsFacade: StatisticsFacade

    @Mock
    protected lateinit var repositoryService: RepositoryService

    @Mock
    protected lateinit var commitServiceFacade: CommitServiceFacade

    @Mock
    protected lateinit var userModelBuilder: UserModelBuilder

    protected val statisticsRestTestHelper = StatisticsRestTestHelper()

    protected val userRestTetHelper = UserRestTetHelper()

    protected val repositoryCommonTestHelper = RepositoryCommonTestHelper()

    protected val commitCommonTestHelper = CommitCommonTestHelper()

    protected val userCommonTestHelper = UserCommonTestHelper()

    @Before
    fun prepare() {
        statisticsFacade = StatisticsFacadeImpl(repositoryService, commitServiceFacade, userModelBuilder)
    }
}