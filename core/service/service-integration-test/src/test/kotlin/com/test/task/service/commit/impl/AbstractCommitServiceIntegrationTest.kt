package com.test.task.service.commit.impl

import com.test.task.helper.interation.commit.CommitIntegrationTestHelper
import com.test.task.helper.interation.repository.RepositoryIntegrationTestHelper
import com.test.task.helper.interation.user.UserIntegrationTestHelper
import com.test.task.service.AbstractServiceIntegrationTest
import com.test.task.service.commit.CommitService
import org.springframework.beans.factory.annotation.Autowired


abstract class AbstractCommitServiceIntegrationTest : AbstractServiceIntegrationTest() {

    @Autowired
    protected lateinit var commitService: CommitService

    @Autowired
    protected lateinit var userIntegrationTestHelper: UserIntegrationTestHelper

    @Autowired
    protected lateinit var repositoryIntegrationTestHelper: RepositoryIntegrationTestHelper

    @Autowired
    protected lateinit var integrationTestHelper: CommitIntegrationTestHelper
}