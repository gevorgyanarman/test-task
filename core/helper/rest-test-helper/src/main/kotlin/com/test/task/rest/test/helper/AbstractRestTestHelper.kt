package com.test.task.rest.test.helper

import com.test.task.commons.api.model.model.response.ErrorResponseModel
import com.test.task.commons.api.model.model.response.ResultResponseModel
import org.assertj.core.api.Assertions.assertThat
import java.util.*
import kotlin.random.Random


abstract class AbstractRestTestHelper {
    fun uuId(): String = UUID.randomUUID().toString()

    fun randomLong(): Long = Random.nextLong()

    fun assertBasicSuccessResultResponse(resultResponse: ResultResponseModel<*, *>) {
        assertThat(resultResponse).isNotNull
        assertThat(resultResponse.success()).isTrue()
        assertThat(resultResponse.errors()).isEmpty()
    }

    fun assertBasicErrorResultResponse(resultResponse: ResultResponseModel<*, *>, vararg errors: ErrorResponseModel) {
        assertThat(resultResponse).isNotNull
        assertThat(resultResponse.success()).isFalse()
        assertThat(resultResponse.errors()).isNotEmpty.containsExactlyInAnyOrder(*errors)
    }
}