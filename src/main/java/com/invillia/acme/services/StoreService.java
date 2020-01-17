package com.invillia.acme.services;

import com.invillia.acme.domain.Store;
import com.invillia.acme.exceptions.EntityNotFoundException;
import com.invillia.acme.exceptions.OperationFailedException;

public interface StoreService {
	
	Store save(Store store) throws OperationFailedException;
	
	Store getStoreByCode(Long code) throws OperationFailedException;
	
}
