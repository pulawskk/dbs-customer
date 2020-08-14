package com.pulawskk.dbscustomer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BurgerDtoList {

    List<BurgerDto> burgers;
}
