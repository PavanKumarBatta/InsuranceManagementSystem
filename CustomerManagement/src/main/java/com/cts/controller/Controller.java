package com.cts.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.model.Customer;
import com.cts.service.Services;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/customer")
@RequiredArgsConstructor
public class Controller {
	
	private final Services service;
	
	@PostMapping("/save")
	public Mono<String> save(@RequestBody Customer customer) {
		
		return service.save(customer);
	}
	
	@GetMapping("/find/{id}")
	public Mono<Customer> find(@PathVariable long id) {
		
		return service.find(id);
	}
}
