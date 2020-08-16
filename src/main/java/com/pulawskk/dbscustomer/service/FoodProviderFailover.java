package com.pulawskk.dbscustomer.service;

import com.pulawskk.dbscustomer.model.BurgerDtoList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FoodProviderFailover implements FoodProviderFeignClient {

    private final FoodProviderFailoverFeignClient foodProviderFailoverFeignClient;

    @Override
    public ResponseEntity<BurgerDtoList> getBurgers() {
        return foodProviderFailoverFeignClient.getBurgers();
    }
}
