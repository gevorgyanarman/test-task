package com.test.task.service.respository.impl

import com.test.task.helper.unit.repository.RepositoryCommonTestHelper
import com.test.task.repository.repository.RepositoryRepository
import com.test.task.service.AbstractServiceUnitTest
import com.test.task.service.repository.RepositoryService
import org.easymock.Mock
import org.junit.Before


abstract class AbstractRepositoryServiceImplUnitTest : AbstractServiceUnitTest() {

    protected lateinit var repositoryService: RepositoryService

    @Mock
    protected lateinit var repositoryRepository: RepositoryRepository

    protected val commonTestHelper = RepositoryCommonTestHelper()

    @Before
    fun prepare() {
        repositoryService = RepositoryServiceImpl(repositoryRepository)
    }
}