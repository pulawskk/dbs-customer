package com.pulawskk.dbscustomer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BurgerDto {
    private String id;
    private String name;
    private String price;
    private String ingredientsJson;
    private String order_url;
}
