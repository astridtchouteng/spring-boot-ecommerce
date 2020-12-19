package com.shop.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.ecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
