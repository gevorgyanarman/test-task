package com.test.task.helper

import java.util.*
import kotlin.random.Random


abstract class AbstractTestHelper {
    fun uuId(): String = UUID.randomUUID().toString()

    fun randomLong(): Long = Random.nextLong()
}