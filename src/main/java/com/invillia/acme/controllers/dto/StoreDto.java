package com.invillia.acme.controllers.dto;

public class StoreDto {

	private Long code;
	
	private String address;
	
	private String name;
	
	private String errors;
	

	public StoreDto() {
		super();
	}

	public StoreDto(String errors) {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}
	
	
}
