package com.shop.ecommerce.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.ecommerce.entity.Product;

@CrossOrigin("http://localhost:4200")
public interface ProductRepository extends JpaRepository<Product, Long> {

	/*
	 * Spring Data REST automatically exposes endpoints for us
	 * http://localhost:8282/api/products/search/findByCategoryId?id:id
	 * */
	Page<Product> findByCategoryId(@RequestParam("id") Long id, Pageable pageable);
	
	
	/*
	 * behind the scene Spring performs this request method for me
	 * SELECT * FROM p
	 * WHERE
	 * p.name LIKE CONCAT('%', :name, '%')
	 * */
	Page<Product> findByNameContaining(@RequestParam("name") String name, Pageable pageable);
	
}
