package com.invillia.acme.controllers;

import java.util.Date;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.invillia.acme.controllers.dto.RefundOrderResponse;
import com.invillia.acme.domain.Order;
import com.invillia.acme.domain.OrderStatus;
import com.invillia.acme.domain.Payment;
import com.invillia.acme.domain.PaymentStatus;

import io.swagger.annotations.ApiOperation;

@RestController
public class OrderController {

	@ApiOperation("Get a specific order by code transaction.")
	@GetMapping("/order/{code}")
	public Order getOrder(@PathVariable("code") Long code) {
		return new Order(code, "St. 23", new Date(), OrderStatus.APPROVE_PAYMENT);
	}
	
	@ApiOperation("Create a new Order.")
	@PostMapping("/order")
	public Order postOrder(@RequestBody Order order) {
		return new Order(1L, "St. 23", new Date(), OrderStatus.CREATED);
	}
	
	@ApiOperation("Refund a specific order passing its code transaction.")
	@PatchMapping("/order/{code}")
	public RefundOrderResponse refundOrder(@PathVariable("code") Long code) {
		return new RefundOrderResponse(1L, OrderStatus.CANCELLED.getDescription());
	}
	
	@ApiOperation("Refund a specific order item passing its code transaction and code item.")
	@PatchMapping("/order/{code}/{codeItem}")
	public RefundOrderResponse refundOrderItem(@PathVariable("code") Long code, @PathVariable("codeItem") Long codeItem) {
		return new RefundOrderResponse(1L, OrderStatus.CANCELLED.getDescription());
	}
	
	@ApiOperation("Create a Payment for a specific order.")
	@PostMapping("/order/payment/{code}")
	public Payment postPaymentOrder(@RequestBody Payment payment, @PathVariable("code") Long code) {
		Order order = new Order(code, null, null, null);
		return new Payment(1L, UUID.randomUUID().node(), order, PaymentStatus.PAYMENT_PENDING);
	}
	
	
	
	
	
	
	
}
