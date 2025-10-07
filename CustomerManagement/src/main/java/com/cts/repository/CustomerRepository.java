package com.cts.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.cts.model.Customer;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Long> {
    // ReactiveCrudRepository already provides save(), findById(), findAll(), delete() methods
}
