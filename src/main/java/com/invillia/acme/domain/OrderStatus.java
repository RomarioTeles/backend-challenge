package com.invillia.acme.domain;

public enum OrderStatus {

	CREATED("Order Created"),
	PAYMENT_PENDING("Payment Pending"),
	APPROVE_PAYMENT("Approve Payment"),
	PAYMENT_DENIED("Payment Denied"),
	PAYMENT_APPROVED("Payment Approved"),
	ORDER_CREATE_ERROR("Order Create Error"),
	HANDLING_SHIPPING("Handling Shipping"),
	SHIPPING("Shipping"),
	INVOICED("Invoiced"),
	CANCELLED("Cancelled");
	
	private String description;
	
	private OrderStatus(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
