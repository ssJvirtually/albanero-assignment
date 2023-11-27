package com.albanero.order.repository;

import com.albanero.order.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findByName(String name);

    Product findByPrice(double price);

    List<Product> findAllByPrice(double price);

}

