package com.test.task.gateway.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = {"com.test.task.gateway.resources"})
@EnableFeignClients({"com.test.task"})
public class GatewayConfiguration {
}
