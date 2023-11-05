package com.jakubskc.crudimagegallery.repository;

import com.jakubskc.crudimagegallery.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findByName(String name);
}
