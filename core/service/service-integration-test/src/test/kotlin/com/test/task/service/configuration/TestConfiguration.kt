package com.test.task.service.configuration

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Lazy
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor


@TestConfiguration
@Lazy(false)
@ComponentScan("com.test.task")
@EnableJpaRepositories("com.test.task")
@EntityScan("com.test.task")
class TestConfiguration {

    @Bean
    @Primary
    fun executor(): ThreadPoolTaskExecutor = ThreadPoolTaskExecutor()
}