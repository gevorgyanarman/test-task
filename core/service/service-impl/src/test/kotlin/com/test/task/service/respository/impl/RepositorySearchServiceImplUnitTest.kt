package com.test.task.service.respository.impl

import org.assertj.core.api.Assertions.assertThat
import org.easymock.EasyMock.expect
import org.junit.Test
import org.springframework.data.domain.PageRequest

class RepositorySearchServiceImplUnitTest : AbstractRepositoryServiceImplUnitTest() {

    @Test
    fun `test with invalid argument`() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { repositoryService.search(null) }
        verifyAll()
    }

    @Test
    fun test() {
        val dto = commonTestHelper.buildSearchRepositoryDto()
        val repository1 = commonTestHelper.buildRepository()
        val repository2 = commonTestHelper.buildRepository()
        val elementsCount: Long = 100
        val page = commonTestHelper.buildPage(
                elementsCount = elementsCount,
                repositories = listOf(repository1, repository2)
        )
        resetAll()
        expect(repositoryRepository.search(dto.query, PageRequest.of(dto.page, dto.size))).andReturn(page)
        replayAll()
        repositoryService.search(dto)
                .apply { assertThat(this.totalElements).isEqualTo(elementsCount) }
                .apply { assertThat(this.toList()).containsExactlyInAnyOrder(repository1, repository2) }
        verifyAll()
    }
}