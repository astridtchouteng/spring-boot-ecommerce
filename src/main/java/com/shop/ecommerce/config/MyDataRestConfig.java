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

import com.shop.ecommerce.entity.Country;
import com.shop.ecommerce.entity.Product;
import com.shop.ecommerce.entity.ProductCategory;
import com.shop.ecommerce.entity.State;

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
				
		disableHttpMethod(Product.class, config, unsupportedActions);
		disableHttpMethod(ProductCategory.class, config, unsupportedActions);
		disableHttpMethod(Country.class, config, unsupportedActions);
		disableHttpMethod(State.class, config, unsupportedActions);
		
		//call an internal helper method
		exposeIds(config);
	}

	private void disableHttpMethod(Class theClass, RepositoryRestConfiguration config, HttpMethod[] unsupportedActions) {
		config.getExposureConfiguration()
				.forDomainType(theClass)
				.withItemExposure((metadata, httpMethods) -> httpMethods.disable(unsupportedActions))
				.withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(unsupportedActions));
	}

	private void exposeIds(RepositoryRestConfiguration config) {
		
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
