package com.invillia.acme.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invillia.acme.domain.Store;
import com.invillia.acme.exceptions.EntityNotFoundException;
import com.invillia.acme.exceptions.OperationFailedException;
import com.invillia.acme.repositories.StoreRepository;
import com.invillia.acme.services.StoreService;

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreRepository repository;
	
	@Override
	public Store save(Store store) throws OperationFailedException {
		try {
			return repository.save(store);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OperationFailedException("Internal Error. Please, try again!");
		}
	}

	@Override
	public Store getStoreByCode(Long code) throws OperationFailedException {
		try {
			Store store = repository.findByCode(code);
			return store;
		} catch (Exception e) {
			throw new OperationFailedException("Internal Error. Please, try again!");
		}
	}

}
