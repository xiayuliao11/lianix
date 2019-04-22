package com.lsl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.lsl.mapper")
public class ShopProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopProducerApplication.class, args);
    }

}
