package com.invillia.acme.controllers.viewModels;

import com.invillia.acme.controllers.dto.StoreDto;
import com.invillia.acme.domain.Store;
import com.invillia.acme.exceptions.EntityAlreadyExistsException;
import com.invillia.acme.exceptions.EntityNotFoundException;
import com.invillia.acme.exceptions.NoMatchEntityException;
import com.invillia.acme.exceptions.OperationFailedException;
import com.invillia.acme.services.StoreService;

public class StoreViewModel{
	
	public static void update(StoreService storeService, StoreDto store, Long code) throws OperationFailedException, NoMatchEntityException, EntityNotFoundException {
		
		Store entity = storeService.getStoreByCode(code);
		if(entity != null) {
			if(entity.getCode().equals(store.getCode())) {
				entity.setName(store.getName());
				entity.setAddress(store.getAddress());
				entity.setCode(store.getCode());
				storeService.save(entity);
			}else {
				throw new NoMatchEntityException();
			}
		}else {
			throw new EntityNotFoundException("Store Not Found!");
		}
		
	}

	public static void save(StoreService storeService, StoreDto store) throws OperationFailedException, EntityAlreadyExistsException {
		
		Store entity = storeService.getStoreByCode(store.getCode());
		if(entity == null) {
			entity = new Store();
		}else {
			throw new EntityAlreadyExistsException("Store is already registred!");
		}
		
		entity.setName(store.getName());
		entity.setAddress(store.getAddress());
		entity.setCode(store.getCode());
		storeService.save(entity);
	}

	public static StoreDto getStoreByCode(StoreService storeService, Long code) throws OperationFailedException, EntityNotFoundException {
		Store store = storeService.getStoreByCode(code);
		if(store == null) {
			throw new EntityNotFoundException("Store fot Found!");
		}
		return store.getDto();
	}

}
