package com.cts.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.model.Customer;
import com.cts.service.CustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
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
    public Mono<ResponseEntity<Customer>> find(@PathVariable Long id) {
        return service.findById(id);
    }
    
    @PutMapping("/update/{id}")
    public Mono<Customer>update(@PathVariable Long id,@RequestBody Customer customer){
    	
    	return service.update(id,customer);
    }
    @DeleteMapping("delete/{id}")
    public Mono<ResponseEntity<String>> delete(@PathVariable Long id) {
    	
    	return service.delete(id);
    }
    @GetMapping("/findAll")
    public Flux<Customer> getAll(){
    	return service.getAll();
    }
}
