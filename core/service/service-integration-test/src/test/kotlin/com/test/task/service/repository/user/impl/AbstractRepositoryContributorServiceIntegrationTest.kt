package com.test.task.service.repository.user.impl

import com.test.task.helper.interation.repository.RepositoryIntegrationTestHelper
import com.test.task.helper.interation.repository.user.RepositoryContributorIntegrationTestHelper
import com.test.task.helper.interation.user.UserIntegrationTestHelper
import com.test.task.service.AbstractServiceIntegrationTest
import com.test.task.service.repository.user.RepositoryContributorService
import org.springframework.beans.factory.annotation.Autowired


abstract class AbstractRepositoryContributorServiceIntegrationTest : AbstractServiceIntegrationTest() {

    @Autowired
    protected lateinit var repositoryContributorService: RepositoryContributorService

    @Autowired
    protected lateinit var userIntegrationTestHelper: UserIntegrationTestHelper

    @Autowired
    protected lateinit var repositoryIntegrationTestHelper: RepositoryIntegrationTestHelper

    @Autowired
    protected lateinit var integrationTestHelper: RepositoryContributorIntegrationTestHelper
}