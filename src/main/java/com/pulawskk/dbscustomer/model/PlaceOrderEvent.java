package com.pulawskk.dbscustomer.model;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class PlaceOrderEvent {

    private Long id;

    private String deliveryName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZIP;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;
    private LocalDateTime placedAt;

    private String orderUrl;

    private String userId;

    private Long burgerId;
}

