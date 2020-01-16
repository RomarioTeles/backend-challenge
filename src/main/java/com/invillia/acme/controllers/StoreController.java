package com.invillia.acme.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.invillia.acme.domain.Store;

import io.swagger.annotations.ApiOperation;

@RestController
public class StoreController {

	@ApiOperation("Get a specific store by code transaction.")
	@GetMapping("/store/{code}")
	public Store getStore(@PathVariable("code") Long code) {
		return new Store(code, "St. 23", "My Store");
	}
	
	@ApiOperation("Create a new Store.")
	@PostMapping("/store")
	public Store postStore(@RequestBody Store store) {
		return store;
	}
	
	@ApiOperation("Update a new Store.")
	@PutMapping("/store/{id}")
	public Store putStore(@RequestBody Store store, @PathVariable("id") Long id) {
		return store;
	}
	
}
