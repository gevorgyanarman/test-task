package com.test.task.service.respository.impl

import com.test.task.helper.unit.repository.RepositoryCommonTestHelper
import com.test.task.service.AbstractServiceUnitTest
import com.test.task.service.repository.RepositoryCompoundActionService
import com.test.task.service.repository.RepositoryService
import org.assertj.core.api.Assertions.assertThat
import org.easymock.EasyMock.expect
import org.easymock.Mock
import org.junit.Before
import org.junit.Test
import java.util.*


class RepositoryCompoundActionServiceImplUnitTest : AbstractServiceUnitTest() {

    protected lateinit var repositoryCompoundActionService: RepositoryCompoundActionService

    @Mock
    private lateinit var repositoryService: RepositoryService

    private val commonTestHelper = RepositoryCommonTestHelper()

    @Before
    fun prepare() {
        repositoryCompoundActionService = RepositoryCompoundActionServiceImpl(repositoryService)
    }

    @Test
    fun `test with invalid argument`() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { repositoryCompoundActionService.createOrUpdate(null) }
        verifyAll()
    }

    @Test
    fun `test when not found`() {
        val dto = commonTestHelper.buildCreateOrUpdateRepositoryDto()
        val createDto = commonTestHelper.buildCreateRepositoryDto(
                remoteId = dto.remoteId,
                name = dto.name,
                fullName = dto.fullName
        )
        val repository = commonTestHelper.buildRepository()
        resetAll()
        expect(repositoryService.findByRemoteId(dto.remoteId)).andReturn(Optional.empty())
        expect(repositoryService.create(createDto)).andReturn(repository)
        replayAll()
        repositoryCompoundActionService.createOrUpdate(dto)
                .apply { assertThat(this).isEqualTo(repository) }
        verifyAll()
    }

    @Test
    fun `test when found`() {
        val repositoryId = randomLong()
        val dto = commonTestHelper.buildCreateOrUpdateRepositoryDto()
        val repository = commonTestHelper.buildRepository().apply { this.id = repositoryId }
        val updateDto = commonTestHelper.buildUpdateRepositoryDto(
                id = repository.id,
                name = dto.name,
                fullName = dto.fullName
        )
        val updatedRepository = commonTestHelper.buildRepository()
        resetAll()
        expect(repositoryService.findByRemoteId(dto.remoteId)).andReturn(Optional.of(repository))
        expect(repositoryService.update(updateDto)).andReturn(updatedRepository)
        replayAll()
        repositoryCompoundActionService.createOrUpdate(dto)
                .apply { assertThat(this).isEqualTo(updatedRepository) }
        verifyAll()
    }
}