package com.pulawskk.dbscustomer.service;

import com.pulawskk.dbscustomer.model.BurgerDtoList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "dbs-food")
public interface FoodProviderFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = PlaceOrderService.BURGER_BASE_URL)
    ResponseEntity<BurgerDtoList> getBurgers();

}
