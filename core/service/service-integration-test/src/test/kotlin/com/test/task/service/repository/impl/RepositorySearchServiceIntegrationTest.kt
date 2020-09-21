package com.test.task.service.repository.impl

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class RepositorySearchServiceIntegrationTest : AbstractRepositoryServiceIntegrationTest() {

    @Test
    fun `test when nothing was found`() {
        println()
        repeat(10) { integrationTestHelper.persistRepository() }
        flushAndClear()
        val dto = integrationTestHelper.buildSearchRepositoryDto()
        repositoryService.search(dto)
                .apply { assertThat(this).isEmpty() }.toList()
    }

    @Test
    fun test() {
        val fullName1 = "ABCDEFGHIJKLMNOP"
        val fullName2 = "ABCDEFGHIJKLMNOPQRST"
        val repository1 = integrationTestHelper.persistRepository(fullName = fullName1)
        val repository2 = integrationTestHelper.persistRepository(fullName = fullName2)
        repeat(10) { integrationTestHelper.persistRepository() }
        flushAndClear()
        val dto = integrationTestHelper.buildSearchRepositoryDto(query = "FGHIJK")
        repositoryService.search(dto)
                .apply { assertThat(this).isNotEmpty }.toList()
                .apply { assertThat(this.size).isEqualTo(2) }
                .apply { assertThat(this[0]).isEqualTo(repository2) }
                .apply { assertThat(this[1]).isEqualTo(repository1) }
    }
}