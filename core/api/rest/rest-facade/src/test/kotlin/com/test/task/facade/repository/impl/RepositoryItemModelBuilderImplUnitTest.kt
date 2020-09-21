package com.test.task.facade.repository.impl

import com.test.task.facade.AbstractFacadeUnitTest
import com.test.task.helper.unit.repository.RepositoryCommonTestHelper
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class RepositoryItemModelBuilderImplUnitTest : AbstractFacadeUnitTest() {

    private lateinit var builder: RepositoryItemModelBuilder

    private val commonTestHelper = RepositoryCommonTestHelper()

    @Before
    fun prepare() {
        builder = RepositoryItemModelBuilderImpl()
    }

    @Test
    fun `test with invalid argument`() {
        resetAll()
        replayAll()
        assertIllegalArgumentException { builder.build(null) }
        verifyAll()
    }

    @Test
    fun test() {
        val repository = commonTestHelper.buildRepository()
        resetAll()
        replayAll()
        builder.build(repository)
                .apply { assertThat(this.uuId).isEqualTo(repository.uuId) }
                .apply { assertThat(this.name).isEqualTo(repository.name) }
                .apply { assertThat(this.fullName).isEqualTo(repository.fullName) }
        verifyAll()
    }

}