package com.test.task.gateway.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/", produces = "text/plain")
public class HealthCheckResource {

    @GetMapping(path = "/health")
    public String health() {
        return "OK";
    }

}
