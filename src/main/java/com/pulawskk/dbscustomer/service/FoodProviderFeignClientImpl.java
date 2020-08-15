package com.pulawskk.dbscustomer.service;

import com.pulawskk.dbscustomer.model.BurgerDtoList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Profile("local-discovery")
@Slf4j
@Service
public class FoodProviderFeignClientImpl implements FoodProviderFeignClient, FoodProvider {

    private final FoodProviderFeignClient foodProviderFeignClient;

    @Override
    public ResponseEntity<BurgerDtoList> getBurgers() {
        ResponseEntity<BurgerDtoList> burgers = foodProviderFeignClient.getBurgers();
        log.debug(String.format("%d burgers ha(s)ve been loaded.",
                Optional.ofNullable(burgers.getBody()).orElse(new BurgerDtoList()).getBurgers().size()));
        return burgers;
    }

    @Override
    public BurgerDtoList getBurgerDtoList() {
        return Optional.ofNullable(getBurgers().getBody()).orElse(new BurgerDtoList());
    }
}
