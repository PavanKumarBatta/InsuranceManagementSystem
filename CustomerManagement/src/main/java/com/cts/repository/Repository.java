package com.cts.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.cts.model.Customer;

// No need for custom @Query for simple insert.
// ReactiveCrudRepository provides save(Customer) which handles it.
public interface Repository extends ReactiveCrudRepository<Customer,Long> {

    // The method insertCustomer has been REMOVED.
    // Use the inherited Mono<Customer> save(Customer customer) method instead.
}