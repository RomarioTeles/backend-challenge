package com.invillia.acme.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.invillia.acme.controllers.dto.OrderDto;
import com.invillia.acme.controllers.dto.PaymentDto;
import com.invillia.acme.controllers.viewModels.OrderViewModel;
import com.invillia.acme.exceptions.EntityAlreadyExistsException;
import com.invillia.acme.exceptions.EntityNotFoundException;
import com.invillia.acme.exceptions.OperationFailedException;
import com.invillia.acme.exceptions.RefundDateException;
import com.invillia.acme.services.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("Order")
@Validated
@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	@ApiOperation("Get a specific order by code transaction.")
	@GetMapping("order/{code}")
	public ResponseEntity<OrderDto> getOrder(@PathVariable("code") Long code) {
		if(code == null) {
			return ResponseEntity.badRequest().build();
		}else {
			try {
				OrderDto orderDto = OrderViewModel.getOrderByCode(orderService, code);
				return new ResponseEntity<OrderDto>(orderDto, HttpStatus.OK);
			} catch (OperationFailedException e) {
				return ResponseEntity.status(500).build();
			}catch (EntityNotFoundException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		}
	}
	
	@ApiOperation("Create a new Order.")
	@PostMapping("/order")
	public ResponseEntity<OrderDto> postOrder(@Valid @RequestBody OrderDto order) {
		if(order == null) {
			return ResponseEntity.badRequest().build();
		}else {
			try {
				OrderViewModel.save(orderService, order);
				URI location = ServletUriComponentsBuilder
	                    .fromCurrentRequest()
	                    .path("/{code}")
	                    .buildAndExpand(order.getCode())
	                    .toUri();
				return ResponseEntity.created(location).body(order);
			} catch (OperationFailedException e) {
				System.out.println(e.getMessage());
				return ResponseEntity.status(500).build();
			}catch (EntityAlreadyExistsException e) {
				order.setErrors(e.getMessage());
				return ResponseEntity.status(HttpStatus.CONFLICT).body(order);
			}catch (Exception e) {
				System.out.println(e.getMessage());
				return ResponseEntity.status(500).build();
			}
			
		}
	}
	
	@ApiOperation("Refund a specific order passing its code transaction.")
	@PatchMapping("/order/refund/{code}")
	public ResponseEntity<OrderDto> refundOrder(@PathVariable("code") Long code) {
		if(code == null) {
			return ResponseEntity.badRequest().build();
		}else {
			try {
				OrderDto orderDto = OrderViewModel.refundOrder(orderService, code);
				return new ResponseEntity<OrderDto>(orderDto, HttpStatus.OK);
			} catch (OperationFailedException e) {
				return ResponseEntity.status(500).build();
			}catch (EntityNotFoundException e) {
				return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			} catch (RefundDateException e) {
				return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
						.body(new OrderDto(e.getMessage()));
			}
		}
	}
	
	@ApiOperation("Refund a specific order item passing its code transaction and code item.")
	@PatchMapping("/order/refund/{code}/item/{codeItem}")
	public ResponseEntity<OrderDto> refundOrderItem(@PathVariable("code") Long code, @PathVariable("codeItem") Long codeItem) {
		if(code == null) {
			return ResponseEntity.badRequest().build();
		}else {
			try {
				OrderDto orderDto = OrderViewModel.refundOrderItem(orderService, code, codeItem);
				return new ResponseEntity<OrderDto>(orderDto, HttpStatus.OK);
			} catch (OperationFailedException e) {
				return ResponseEntity.status(500).build();
			}catch (EntityNotFoundException e) {
				return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			} catch (RefundDateException e) {
				return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
						.body(new OrderDto(e.getMessage()));
			}
		}
	}
	
	@ApiOperation("Create a Payment for a specific order.")
	@PostMapping("/order/payment")
	public ResponseEntity<OrderDto> postPaymentOrder(@RequestBody PaymentDto payment) {
		if(payment == null) {
			return ResponseEntity.badRequest().build();
		}else {
			try {
				OrderDto orderDto = OrderViewModel.createPayment(orderService, payment);
				return new ResponseEntity<OrderDto>(orderDto, HttpStatus.OK);
			} catch (OperationFailedException e) {
				return ResponseEntity.status(500).build();
			}catch (EntityNotFoundException e) {
				return  ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new OrderDto(e.getMessage()));
			}
		}
	}
	
	
	
	
	
	
	
}
