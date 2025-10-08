package com.cts.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.model.Policy;
import com.cts.service.PolicyService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/policy")
public class PolicyController {
	
	private final PolicyService service;
	
	public PolicyController(PolicyService service) {
		this.service=service;
	}

	@PostMapping("/save")
	public Mono<Policy> save(@RequestBody Policy policy){
		
		return service.save(policy);
	}
	
	@GetMapping("/find/{id}")
	public Mono<Policy> get(@PathVariable Long id){
		
		return service.get(id);
	}
	
	@GetMapping("/findAll")
	public Flux<Policy>getAll(){
		return service.getAll();
	}
	
	@PutMapping("/update/{id}")
	public Mono<Policy>update(@PathVariable Long id,@RequestBody Policy policy){
		
		return service.update(id,policy);
	}
}
