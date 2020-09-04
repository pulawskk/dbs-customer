package com.pulawskk.dbscustomer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.pulawskk.dbscustomer.model.BurgerDtoList;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static com.pulawskk.dbscustomer.service.PlaceOrderService.BURGER_BASE_URL;

@Service
@ConfigurationProperties(value = "dbs.food")
public class FoodProviderRestTemplateImpl implements FoodProviderRestTemplate, FoodProvider {

    private final RestTemplateBuilder restTemplateBuilder;
    private RestTemplate restTemplate;
    private String apihost;

    public FoodProviderRestTemplateImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
        restTemplate = restTemplateBuilder.build();
    }

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }

    @Override
    public BurgerDtoList getBurgerDtoList() {
        ResponseEntity<BurgerDtoList> response = restTemplate.getForEntity(apihost + BURGER_BASE_URL, BurgerDtoList.class);
        return Optional.ofNullable(response.getBody()).orElse(new BurgerDtoList());
    }
}
