package com.test.task.service

import com.test.task.service.configuration.ServiceIntegrationTestDbInitializerConfiguration
import com.test.task.service.configuration.TestConfiguration
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import kotlin.random.Random

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = [ServiceIntegrationTestDbInitializerConfiguration::class, TestConfiguration::class])
abstract class AbstractServiceIntegrationTest : AbstractTransactionalJUnit4SpringContextTests() {

    @PersistenceContext
    lateinit var entityManager: EntityManager

    fun uuId(): String = UUID.randomUUID().toString()

    fun randomLong(): Long = Random.nextLong()

    fun flush() {
        entityManager.flush()
    }

    fun clear() {
        entityManager.clear()
    }

    fun flushAndClear() {
        entityManager.flush()
        entityManager.clear()
    }
}