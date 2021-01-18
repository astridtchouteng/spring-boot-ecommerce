package com.shop.ecommerce.service;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.shop.ecommerce.dao.CustomerRepository;
import com.shop.ecommerce.dto.Purchase;
import com.shop.ecommerce.dto.PurchaseResponse;
import com.shop.ecommerce.entity.Customer;
import com.shop.ecommerce.entity.Order;

@Service
public class CheckoutServiceImpl implements CheckoutService {

	private CustomerRepository customerRepository;
		
	public CheckoutServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	@Transactional
	public PurchaseResponse placeOrder(Purchase purchase) {
		
		// retrieve the order info from the dto
		Order order = purchase.getOrder();
		System.out.println(order.getTotalQuantity());
		
		// generate tracking number
		String orderTrackingNumber = generateOrderTrackingNUmber();
		
		order.setOrderTrackingNumber(orderTrackingNumber);
		
		// populate order with billing and shipping address
		order.setBillingAddress(purchase.getBillingAddress());
		order.setShippingAddress(purchase.getShippingAddress());
		
		// populate order with orderItems;
		purchase.getOrderItems().forEach(item -> order.add(item));
		
		// populate customer with order
		Customer customer = purchase.getCustomer();
		customer.add(order);
		
		// save to the database
		customerRepository.save(customer);
		
		// return a response
		return new PurchaseResponse(orderTrackingNumber);
	}

	private String generateOrderTrackingNUmber() {
		return UUID.randomUUID().toString();
	}

}
