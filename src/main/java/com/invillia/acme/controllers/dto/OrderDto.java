package com.invillia.acme.controllers.dto;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.invillia.acme.domain.OrderStatus;

public class OrderDto {
	
	@NotNull(message="Fill order code.")
	private Long code;

	@NotNull(message="Fill order address.")
	private String address;

	@NotNull(message="Fill order confirmation date.")
	private Date confirmationDate;

	@NotNull(message="Fill order status.")
	private OrderStatus status;
	
	private Set<OrderItemDto> items;

	private String errors;
	

	public OrderDto() {
		super();
	}

	public OrderDto(String errors) {
		super();
		this.errors = errors;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getConfirmationDate() {
		return confirmationDate;
	}

	public void setConfirmationDate(Date confirmationDate) {
		this.confirmationDate = confirmationDate;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Set<OrderItemDto> getItems() {
		return items;
	}

	public void setItems(Set<OrderItemDto> items) {
		this.items = items;
	}

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}
	
	
}
