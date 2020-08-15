package com.pulawskk.dbscustomer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.pulawskk.dbscustomer.config.JmsConfig;
import com.pulawskk.dbscustomer.model.BurgerDto;
import com.pulawskk.dbscustomer.model.BurgerDtoList;
import com.pulawskk.dbscustomer.model.PlaceOrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Component
public class PlaceOrderService {

    private final FoodProvider foodProvider;

    public final static String BURGER_BASE_URL = "/api/v1/burgers";
    private final static ThreadLocalRandom random = ThreadLocalRandom.current();

    private List<String> users;
    private final Faker faker;
    private final JmsTemplate jmsTemplate;

    private final ObjectMapper objectMapper;

    public PlaceOrderService(JmsTemplate jmsTemplate, RestTemplateBuilder restTemplateBuilder, FoodProvider foodProvider, ObjectMapper objectMapper) {
        this.jmsTemplate = jmsTemplate;
        this.foodProvider = foodProvider;
        this.objectMapper = objectMapper;

        faker = new Faker();

        generateUsersId();
    }

    private void generateUsersId() {
        users = new ArrayList<>();
        users.add("123456");
        users.add("555544");
        users.add("678988");
        users.add("190990");
    }

    @Scheduled(fixedRate = 10_000)
    void sendOrder() throws JsonProcessingException {
        PlaceOrderEvent event = preparePlaceOrderEvent();
        Object placeOrderEventMessage = objectMapper.writeValueAsString(event);
        jmsTemplate.convertAndSend(JmsConfig.QUEUE_PLACE_ORDER, placeOrderEventMessage);
        log.debug("[CUSTOMER] Order has been placed: " + event.getDeliveryName());
    }

    private PlaceOrderEvent preparePlaceOrderEvent() {

        return PlaceOrderEvent.builder()
                .deliveryName(faker.name().title())
                .deliveryCity(faker.address().city())
                .deliveryState(faker.address().state())
                .deliveryStreet(faker.address().streetName())
                .deliveryZIP(faker.address().zipCode())
                .ccNumber(faker.business().creditCardNumber())
                .ccExpiration(faker.business().creditCardExpiry())
                .ccCVV(String.valueOf(faker.number().numberBetween(100, 999)))
                .userId(randomUserId())
                .burgerId(randomBurgerId())
                .build();
    }

    private Long randomBurgerId() {
        List<BurgerDto> burgers = foodProvider.getBurgerDtoList().getBurgers();
        return Long.valueOf(burgers.get(random.nextInt(burgers.size())).getId());
    }

    private String randomUserId() {
        return users.get(random.nextInt(0, users.size()));
    }
}
