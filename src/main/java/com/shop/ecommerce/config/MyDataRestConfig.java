package com.shop.ecommerce.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.shop.ecommerce.entity.Product;
import com.shop.ecommerce.entity.ProductCategory;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
	
	private EntityManager entityManager;
	
	@Autowired
	public MyDataRestConfig(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

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
	
	
		//call an internal helper method
		exposeIds(config);
	}

	private void exposeIds(RepositoryRestConfiguration config) {
		
		// expose entity ids
		
		
		// get a list of all entity classes from the entirymanger
		Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
		
		// create an array of the entity types
		List<Class> entityClasses = new ArrayList<Class>();
		
		//get the entity types for the entities
		for (EntityType theEntityType : entities) {
			entityClasses.add(theEntityType.getJavaType());
		}
		
		// expose the entity ids for the array of entity types
		Class[] domainTypes = entityClasses.toArray(new Class[0]);
		
		config.exposeIdsFor(domainTypes);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
}
