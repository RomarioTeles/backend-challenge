package com.invillia.acme.domain;

public enum PaymentStatus {
	
	PAYMENT_PENDING("Payment Pending"),
	APPROVE_PAYMENT("Approve Payment"),
	PAYMENT_DENIED("Payment Denied"),
	PAYMENT_APPROVED("Payment Approved");
	
    private String description;
	
	private PaymentStatus(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
