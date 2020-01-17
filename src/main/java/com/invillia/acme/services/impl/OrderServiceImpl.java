package com.invillia.acme.services.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.invillia.acme.controllers.dto.PaymentDto;
import com.invillia.acme.domain.Order;
import com.invillia.acme.domain.OrderStatus;
import com.invillia.acme.domain.Payment;
import com.invillia.acme.exceptions.EntityNotFoundException;
import com.invillia.acme.exceptions.OperationFailedException;
import com.invillia.acme.exceptions.RefundDateException;
import com.invillia.acme.repositories.OrderRepository;
import com.invillia.acme.services.OrderService;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository repository;

	@Override
	public Order getOrderByCode(Long code) throws OperationFailedException{
		try {
			Order order = repository.findByCode(code);
			return order;
		} catch (Exception e) {
			throw new OperationFailedException("Internal Error. Please, try again!");
		}
	}

	@Override
	public Order refundOrder(Long code) throws RefundDateException, OperationFailedException, EntityNotFoundException {
		Order order = getOrderByCode(code);
		if (order != null) {
			if (isDateRefundValid(order.getConfirmationDate())) {
				order.setStatus(OrderStatus.CANCELLED);
				return save(order);
			} else {
				throw new RefundDateException("Refund Date is Invalid!");
			}
		} else {
			throw new EntityNotFoundException("Order not found!");
		}
	}

	@Override
	public Order refundOrderItem(Long code, Long codeItem)
			throws RefundDateException, EntityNotFoundException, OperationFailedException {
		Order order = getOrderByCode(code);
		if (order != null) {
			if (isDateRefundValid(order.getConfirmationDate())) {
				if (order.getItems() != null && order.getItems().size() > 0) {
					order.getItems().removeIf(i -> i.getCode().equals(codeItem));
					return save(order);
				} else {
					throw new EntityNotFoundException("Item not found!");
				}
			} else {
				throw new RefundDateException("Refund Date is Invalid!");
			}
		} else {
			throw new EntityNotFoundException("Order not found!");
		}
	}

	@Override
	public Order createPayment(PaymentDto paymentDto) throws EntityNotFoundException, OperationFailedException {
		Order order = getOrderByCode(paymentDto.getCode());
		if (order != null) {
			Payment payment = new Payment();
			payment.setCode(paymentDto.getCode());
			payment.setCreditCardNumber(paymentDto.getCreditCardNumber());
			payment.setOrder(order);
			payment.setPaymentDate(paymentDto.getDate());
			payment.setStatus(paymentDto.getStatus());
			order.setPayment(payment);
			OrderStatus orderStatus = OrderStatus.valueOf(paymentDto.getStatus().name());
			if(orderStatus != null) {
				order.setStatus(orderStatus);
			}
			return save(order);
		} else {
			throw new EntityNotFoundException("Order not found!");
		}
	}

	@Override
	public Order save(Order order) throws OperationFailedException {
		try {
			return repository.save(order);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OperationFailedException("Internal Error. Please, try again!");
		}
	}

	private boolean isDateRefundValid(Date date) {
		if (date == null)
			return false;

		LocalDateTime maxDate = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
		maxDate = maxDate.minusDays(10);
		Instant cofirmationDateInst = Instant.ofEpochMilli(date.getTime());
		LocalDateTime confirmationDate = LocalDateTime.ofInstant(cofirmationDateInst, ZoneId.systemDefault());
		return maxDate.isBefore(confirmationDate);
	}

}
