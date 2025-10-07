package com.cts.service;

import org.springframework.stereotype.Service;

import com.cts.model.Customer;
import com.cts.repository.Repository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ServiceImpl implements Services {

    private final Repository repo;

    @Override
    public Mono<String> save(Customer customer) {
        // Use the built-in save method from ReactiveCrudRepository
        return repo.save(customer)
                .map(savedCustomer -> "Successfully saved Customer with ID: " + savedCustomer.getCustomerId())
                .onErrorResume(e -> {
                    // Log the error for debugging
                    e.printStackTrace(); 
                    return Mono.just("Failed to save: " + e.getLocalizedMessage());
                });
    }

    @Override
    public Mono<Customer> find(long id) {
        return repo.findById(id);
    }
}