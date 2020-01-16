package com.invillia.acme.controllers.dto;

public class RefundOrderResponse {
	private Long code;
	private String message;
	
	public RefundOrderResponse() {
		super();
	}

	public RefundOrderResponse(Long code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
