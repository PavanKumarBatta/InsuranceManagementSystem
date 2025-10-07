package com.cts.service;

import com.cts.model.Customer;
import reactor.core.publisher.Mono;

public interface CustomerService {
    Mono<Customer> save(Customer customer);
    Mono<Customer> findById(Long id);
}
