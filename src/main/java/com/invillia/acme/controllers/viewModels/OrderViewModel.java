package com.invillia.acme.controllers.viewModels;

import java.util.HashSet;

import com.invillia.acme.controllers.dto.OrderDto;
import com.invillia.acme.controllers.dto.PaymentDto;
import com.invillia.acme.domain.Order;
import com.invillia.acme.domain.OrderItem;
import com.invillia.acme.exceptions.EntityAlreadyExistsException;
import com.invillia.acme.exceptions.EntityNotFoundException;
import com.invillia.acme.exceptions.OperationFailedException;
import com.invillia.acme.exceptions.RefundDateException;
import com.invillia.acme.services.OrderService;

public class OrderViewModel {

	public static void save(OrderService orderService, OrderDto order) throws OperationFailedException, EntityNotFoundException, EntityAlreadyExistsException {
		
		Order searchEntity = orderService.getOrderByCode(order.getCode());
		if(searchEntity != null) {
			throw new EntityAlreadyExistsException("Order already registered!");
		}
		
		Order entity = new Order();
		entity.setAddress(order.getAddress());
		entity.setCode(order.getCode());
		entity.setConfirmationDate(order.getConfirmationDate());
		entity.setStatus(order.getStatus());
		entity.setItems(new HashSet<OrderItem>());

		order.getItems().forEach(i -> {
			OrderItem orderItem = new OrderItem();
			orderItem.setCode(i.getCode());
			orderItem.setDescription(i.getDescription());
			orderItem.setOrder(entity);
			orderItem.setQuantity(i.getQuantity());
			orderItem.setUnitPrice(i.getUnitPrice());
			entity.getItems().add(orderItem);
		});

		orderService.save(entity);

	}

	public static OrderDto getOrderByCode(OrderService orderService, Long code) throws OperationFailedException, EntityNotFoundException {
		Order order = orderService.getOrderByCode(code);
		if(order == null) {
			throw new EntityNotFoundException("Order Not Found!");
		}
		return order.getDto();
	}

	public static OrderDto refundOrder(OrderService orderService, Long code)
			throws RefundDateException, OperationFailedException, EntityNotFoundException {
		Order order = orderService.refundOrder(code);
		return order.getDto();
	}

	public static OrderDto refundOrderItem(OrderService orderService, Long code, Long codeItem)
			throws RefundDateException, EntityNotFoundException, OperationFailedException {
		Order order = orderService.refundOrderItem(code, codeItem);
		return order.getDto();
	}

	public static  OrderDto createPayment(OrderService orderService, PaymentDto paymentDto) throws OperationFailedException, EntityNotFoundException {
		Order order = orderService.createPayment(paymentDto);
		return order.getDto();
	}

}

