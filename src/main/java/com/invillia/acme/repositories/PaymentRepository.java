package com.invillia.acme.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.invillia.acme.domain.Payment;

@Service
public interface PaymentRepository extends CrudRepository<Payment, Long>{

}
