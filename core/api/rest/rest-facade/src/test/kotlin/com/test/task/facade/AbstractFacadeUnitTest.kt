package com.test.task.facade

import com.test.task.commons.api.model.model.response.ErrorResponseModel
import com.test.task.commons.api.model.model.response.ResultResponseModel
import org.apache.commons.lang3.RandomUtils
import org.apache.commons.lang3.StringUtils
import org.assertj.core.api.Assertions
import org.easymock.EasyMockRunner
import org.easymock.EasyMockSupport
import org.junit.runner.RunWith
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import kotlin.random.Random

@RunWith(EasyMockRunner::class)
abstract class AbstractFacadeUnitTest : EasyMockSupport() {
    fun uuid(): String = UUID.randomUUID().toString()

    fun randomInt(): Int = Random.nextInt()

    fun randomPositiveInt(): Int = ThreadLocalRandom.current().nextInt(1, Int.MAX_VALUE)

    fun emptyString(): String = StringUtils.EMPTY

    fun statusCode(): Int = RandomUtils.nextInt()

    fun assertBasicSuccessResultResponse(resultResponse: ResultResponseModel<*, *>) {
        Assertions.assertThat(resultResponse).isNotNull
        Assertions.assertThat(resultResponse.success()).isTrue()
        Assertions.assertThat(resultResponse.errors()).isEmpty()
    }

    fun assertBasicErrorResultResponse(resultResponse: ResultResponseModel<*, *>, vararg errors: ErrorResponseModel) {
        Assertions.assertThat(resultResponse).isNotNull
        Assertions.assertThat(resultResponse.success()).isFalse()
        Assertions.assertThat(resultResponse.errors()).isNotEmpty.containsExactlyInAnyOrder(*errors)
    }

    fun assertIllegalArgumentException(action: Action) {
        Assertions.assertThatThrownBy { action.invoke() }.isExactlyInstanceOf(IllegalArgumentException::class.java)
    }
}

typealias Action = () -> Any