package com.raushan.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CouponserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CouponserviceApplication.class, args);
    }

}
