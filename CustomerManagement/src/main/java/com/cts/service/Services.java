package com.cts.service;

import com.cts.model.Customer;

import reactor.core.publisher.Mono;

public interface Services {

	Mono<String> save(Customer customer);

	Mono<Customer> find(long id);

}
