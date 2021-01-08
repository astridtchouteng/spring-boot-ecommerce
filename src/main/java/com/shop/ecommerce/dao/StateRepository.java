package com.shop.ecommerce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.shop.ecommerce.entity.State;

@CrossOrigin("http://localhst:4200")
public interface StateRepository extends JpaRepository<State, Integer> {

	// retrieve states for a given country code
	// the url must be : api/states/search/findByCountryCode?code='IN'
	List<State> findByCountryCode(@Param("code") String code);
}
