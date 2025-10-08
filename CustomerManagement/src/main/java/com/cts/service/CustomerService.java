package com.cts.service;

import org.springframework.http.ResponseEntity;

import com.cts.model.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
    Mono<Customer> save(Customer customer);
    Mono<ResponseEntity<Customer>> findById(Long id);
	Mono<Customer> update(Long id,Customer customer);
	Mono<ResponseEntity<String>> delete(Long id);
	Flux<Customer> getAll();
}
