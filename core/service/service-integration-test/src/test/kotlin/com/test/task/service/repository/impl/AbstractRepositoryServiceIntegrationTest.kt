package com.test.task.service.repository.impl

import com.test.task.helper.interation.repository.RepositoryIntegrationTestHelper
import com.test.task.service.AbstractServiceIntegrationTest
import com.test.task.service.repository.RepositoryService
import org.springframework.beans.factory.annotation.Autowired

abstract class AbstractRepositoryServiceIntegrationTest : AbstractServiceIntegrationTest() {

    @Autowired
    protected lateinit var repositoryService: RepositoryService

    @Autowired
    protected lateinit var integrationTestHelper: RepositoryIntegrationTestHelper
}