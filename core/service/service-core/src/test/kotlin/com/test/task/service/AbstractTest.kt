package com.test.task.service

import org.apache.commons.lang3.StringUtils
import org.assertj.core.api.Assertions
import org.easymock.EasyMockRunner
import org.easymock.EasyMockSupport
import org.junit.runner.RunWith
import java.util.*
import kotlin.random.Random

@RunWith(EasyMockRunner::class)
abstract class AbstractTest : EasyMockSupport() {

    fun uuId(): String = UUID.randomUUID().toString()

    fun randomLong(): Long = Random.nextLong()

    fun emptyString(): String = StringUtils.EMPTY
    
    fun assertIllegalArgumentException(action: Action) {
        Assertions.assertThatThrownBy { action.invoke() }.isExactlyInstanceOf(IllegalArgumentException::class.java)
    }
}

typealias Action = () -> Any