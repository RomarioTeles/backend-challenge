package com.invillia.acme.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.invillia.acme.domain.Store;

@Repository
public interface StoreRepository extends CrudRepository<Store, Long>{

	Store findByCode(Long code);
}
