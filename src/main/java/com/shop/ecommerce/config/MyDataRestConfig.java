package com.shop.ecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.shop.ecommerce.entity.Product;
import com.shop.ecommerce.entity.ProductCategory;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		
		HttpMethod[] unsupportedActions = {HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.POST};
		
		//disable HTTP methods for Products: PUT, POST, DELETE
		//just READ ONLY for products
		
		config.getExposureConfiguration()
				.forDomainType(Product.class)
				.withItemExposure((metadata, httpMethods) -> httpMethods.disable(unsupportedActions))
				.withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(unsupportedActions));
	
		//disable HTTP methods for ProductCategory: PUT, POST, DELETE
				//just READ ONLY for products
				
		config.getExposureConfiguration()
				.forDomainType(ProductCategory.class)
				.withItemExposure((metadata, httpMethods) -> httpMethods.disable(unsupportedActions))
				.withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(unsupportedActions));
	
	
	}

	
}
