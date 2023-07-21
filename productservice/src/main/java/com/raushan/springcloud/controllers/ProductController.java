package com.raushan.springcloud.controllers;

import com.raushan.springcloud.model.Coupon;
import com.raushan.springcloud.model.Product;
import com.raushan.springcloud.repos.ProductRepo;
import com.raushan.springcloud.restclients.CouponClient;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productapi")
@RefreshScope
public class ProductController {

    @Autowired
    CouponClient couponClient;

    @Autowired
    private ProductRepo repo;

    @Value("${com.raushan.springcloud.prop}")
    private String prop;

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    @Retry(name = "product-api", fallbackMethod = "handleError")
    public Product create(@RequestBody Product product){
        Coupon coupon = couponClient.getCoupon(product.getCouponCode());
        product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
        return repo.save(product);
    }

    @RequestMapping(value = "/prop", method = RequestMethod.GET)
    public String getProp(){
        return this.prop;
    }

    public Product handleError(Product product, Exception exception){
        System.out.println("Inside Handle error");
        return product;
    }
}
