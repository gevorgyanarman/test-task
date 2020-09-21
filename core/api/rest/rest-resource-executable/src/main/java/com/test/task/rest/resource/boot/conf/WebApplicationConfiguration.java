package com.test.task.rest.resource.boot.conf;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableFeignClients({"com.test.task.github.client"})
@ComponentScan({"com.test.task"})
@EnableJpaRepositories("com.test.task.repository")
@EntityScan("com.test.task.domain")
@PropertySource("classpath:application.properties")
public class WebApplicationConfiguration {

}
