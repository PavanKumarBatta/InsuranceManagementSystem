package com.cts.controller;

import org.springframework.web.bind.annotation.*;

import com.cts.model.Customer;
import com.cts.service.CustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping("/save")
    public Mono<Customer> save(@Valid @RequestBody Customer customer) {
        return service.save(customer);
    }

    @GetMapping("/find/{id}")
    public Mono<Customer> find(@PathVariable Long id) {
        return service.findById(id);
    }
}
