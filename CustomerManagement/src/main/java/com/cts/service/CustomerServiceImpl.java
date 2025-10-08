package com.cts.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cts.model.Customer;
import com.cts.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    @Override
    public Mono save(Customer customer) {
        customer.setCustomerId(null);
        return repository.save(customer)
        		.map(saved -> ResponseEntity.status(201).body(saved));
    }

    @Override
    public Mono<ResponseEntity<Customer>> findById(Long id) {
        return repository.findById(id)
        		.map(customer->ResponseEntity.ok(customer));
    }

	@Override
	public Mono<Customer> update(Long id,Customer customer) {
		Customer updatedCustomer=Customer.builder()
				.customerId(id)
				.name(customer.getName())
				.email(customer.getEmail())
				.address(customer.getAddress())
				.phone(customer.getPhone())
				.build();
		return repository.save(updatedCustomer);
	}

	@Override
	public Mono delete(Long id) {
		
		repository.deleteById(id);
		return Mono.just(ResponseEntity.status(204).body("Successfully deleted"));
	}

	@Override
	public Flux<Customer> getAll() {
		
		return repository.findAll();
	}
}
