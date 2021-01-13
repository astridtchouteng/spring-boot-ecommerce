package com.shop.ecommerce.dto;

import lombok.Data;

@Data
public class PurchaseResponse {

	// the annotation @Data will generate constructor for final fields
	// or i can use the annotaion @NotNull for this field
	private final String orderTrackingNumber;
}
