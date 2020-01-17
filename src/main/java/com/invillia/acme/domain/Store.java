package com.invillia.acme.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.invillia.acme.controllers.dto.StoreDto;

@Entity
public class Store {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique=true)
	private Long code;
	
	private String address;
	
	private String name;
	
	public Store() {
		super();
	}

	public Store(Long code, String address, String name) {
		super();
		this.code = code;
		this.address = address;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "Store [name=" + name + "]";
	}

	public StoreDto getDto() {
		
		StoreDto dto = new StoreDto();
		dto.setAddress(address);
		dto.setCode(code);
		dto.setName(name);
		
		return dto;
	}
	
}
