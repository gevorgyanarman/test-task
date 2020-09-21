package com.test.task.rest.resource.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestTaskSpringBootApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestTaskSpringBootApplication.class);

    public static void main(String[] args) {
        LOGGER.debug("Starting the Web application");
        SpringApplication.run(TestTaskSpringBootApplication.class, args);
    }
}
