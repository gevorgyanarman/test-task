package com.test.task.migration.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:migration-application.properties")
public class MigrationBootApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(MigrationBootApplication.class);

    public static void main(String[] args) {
        LOGGER.debug("Starting flyway migration");
        SpringApplication.run(MigrationBootApplication.class, args);
    }
}
