package com.raushan.springcloud.repos;

import com.raushan.springcloud.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
