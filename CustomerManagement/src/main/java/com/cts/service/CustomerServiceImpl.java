package com.cts.service;

import org.springframework.stereotype.Service;

import com.cts.model.Customer;
import com.cts.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    @Override
    public Mono<Customer> save(Customer customer) {
        // Remove customerId if present to let DB auto-generate
        customer.setCustomerId(null);
        return repository.save(customer);
    }

    @Override
    public Mono<Customer> findById(Long id) {
        return repository.findById(id);
    }
}
