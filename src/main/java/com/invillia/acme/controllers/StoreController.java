package com.invillia.acme.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.invillia.acme.controllers.dto.StoreDto;
import com.invillia.acme.controllers.viewModels.StoreViewModel;
import com.invillia.acme.exceptions.EntityAlreadyExistsException;
import com.invillia.acme.exceptions.EntityNotFoundException;
import com.invillia.acme.exceptions.NoMatchEntityException;
import com.invillia.acme.exceptions.OperationFailedException;
import com.invillia.acme.services.StoreService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("Store")
@RestController
@Validated
public class StoreController {
	
	@Autowired
	private StoreService storeService;

	@ApiOperation("Get a specific store by code transaction.")
	@GetMapping("/store/{code}")
	public ResponseEntity<StoreDto> getStore(@PathVariable("code") Long code) {
		if(code == null) {
			return ResponseEntity.badRequest().build();
		}else {
			try {
				StoreDto dto = StoreViewModel.getStoreByCode(storeService, code);
				return new ResponseEntity<StoreDto>(dto, HttpStatus.OK);
			} catch (OperationFailedException e) {
				return ResponseEntity.status(500).build();
			}catch (EntityNotFoundException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		}
	}
	
	@ApiOperation("Create a new Store.")
	@PostMapping("/store")
	public ResponseEntity<StoreDto> postStore(@RequestBody StoreDto store) {
		if(store == null) {
			return ResponseEntity.badRequest().build();
		}else {
			try {
				StoreViewModel.save(storeService, store);
				URI location = ServletUriComponentsBuilder
	                    .fromCurrentRequest()
	                    .path("/{code}")
	                    .buildAndExpand(store.getCode())
	                    .toUri();
				return ResponseEntity.created(location).build();
			} catch (OperationFailedException e) {
				return ResponseEntity.status(500).build();
			}catch (EntityAlreadyExistsException e) {
				store.setErrors(e.getMessage());
				return ResponseEntity.status(HttpStatus.CONFLICT).body(store);
			}catch (Exception e) {
				return ResponseEntity.status(500).build();
			}
		}
	}
	
	@ApiOperation("Update a new Store.")
	@PutMapping("/store/{code}")
	public ResponseEntity<StoreDto> putStore(@RequestBody StoreDto store, @PathVariable("code") Long code) {
		if(store == null) {
			return ResponseEntity.badRequest().build();
		}else {
			try {
				StoreViewModel.update(storeService, store, code);
				return ResponseEntity.ok(store);
			} catch (OperationFailedException e) {
				System.out.println(e.getMessage());
				return ResponseEntity.status(500).build();
			}catch (NoMatchEntityException e) {
				store.setErrors("codes do not match.");
				return ResponseEntity.badRequest().body(store);
			} catch (EntityNotFoundException e) {
				return ResponseEntity.notFound().build();
			}catch(Exception e) {
				return ResponseEntity.status(500).build();
			}
			
		}
	}
	
}
