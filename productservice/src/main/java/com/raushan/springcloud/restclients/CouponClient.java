package com.raushan.springcloud.restclients;

import com.raushan.springcloud.model.Coupon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("GATEWAY-SERVICE")
public interface CouponClient {

     @GetMapping("/couponapi/coupons/{code}")
     Coupon getCoupon(@PathVariable("code") String code);
}
