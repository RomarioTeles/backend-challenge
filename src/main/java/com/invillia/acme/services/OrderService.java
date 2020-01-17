package com.invillia.acme.services;


import com.invillia.acme.controllers.dto.PaymentDto;
import com.invillia.acme.domain.Order;
import com.invillia.acme.exceptions.EntityNotFoundException;
import com.invillia.acme.exceptions.OperationFailedException;
import com.invillia.acme.exceptions.RefundDateException;

public interface OrderService {
	
	Order save(Order order) throws OperationFailedException, EntityNotFoundException;
	
	Order getOrderByCode(Long code) throws OperationFailedException;
	
	Order refundOrder(Long code) throws RefundDateException, OperationFailedException, EntityNotFoundException;
	
	Order refundOrderItem(Long code, Long codeItem) throws RefundDateException, EntityNotFoundException, OperationFailedException;
	
	Order createPayment(PaymentDto paymentDto) throws OperationFailedException, EntityNotFoundException;
}
