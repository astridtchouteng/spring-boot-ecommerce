package com.shop.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.shop.ecommerce.entity.Country;

@CrossOrigin("http://localhst:4200")
@RepositoryRestResource(path = "countries", collectionResourceRel = "countries")
public interface CountryRepository extends JpaRepository<Country, Integer> {

}
