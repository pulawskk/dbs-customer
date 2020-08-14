package com.pulawskk.dbscustomer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JmsConfig {

    public static final String QUEUE_PLACE_ORDER = "order-placing-queue";

    private final ObjectMapper objectMapper;

    public JmsConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}