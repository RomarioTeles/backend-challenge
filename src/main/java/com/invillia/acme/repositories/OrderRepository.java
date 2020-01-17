package com.invillia.acme.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.invillia.acme.domain.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long>{

	Order findByCode(Long code);
}
