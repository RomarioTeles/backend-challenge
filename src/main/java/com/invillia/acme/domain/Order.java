package com.invillia.acme.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.invillia.acme.controllers.dto.OrderDto;
import com.invillia.acme.controllers.dto.OrderItemDto;

@Entity
public class Order {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique=true)
	private Long code;

	private String address;

	@Column(name="confirmation_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date confirmationDate;

	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	@OneToMany(mappedBy="order", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<OrderItem> items;
	
	@OneToOne(mappedBy = "order", cascade=CascadeType.ALL, optional=true)
	private Payment payment;
	
	public Order() {
		super();
	}

	public Order(Long code, String address, Date confirmationDate, OrderStatus status) {
		super();
		this.code = code;
		this.address = address;
		this.confirmationDate = confirmationDate;
		this.status = status;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}
	
	public Set<OrderItem> getItems() {
		return items;
	}

	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", code=" + code + ", status=" + status + "]";
	}

	public OrderDto getDto() {
		OrderDto dto = new OrderDto();
		dto.setAddress(address);
		dto.setCode(code);
		dto.setConfirmationDate(confirmationDate);
		dto.setStatus(status);
		Set<OrderItemDto> orderItemDtos = new HashSet<OrderItemDto>();
		if(items != null) {
			items.forEach(i->{
				OrderItemDto item = new OrderItemDto();
				item.setCode(i.getCode());
				item.setDescription(i.getDescription());
				item.setQuantity(i.getQuantity());
				item.setUnitPrice(i.getUnitPrice());
				orderItemDtos.add(item);
			});
		}
		dto.setItems(orderItemDtos);
		
		return dto;
	}
}
