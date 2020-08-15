package com.pulawskk.dbscustomer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class DbsCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbsCustomerApplication.class, args);
    }

}
