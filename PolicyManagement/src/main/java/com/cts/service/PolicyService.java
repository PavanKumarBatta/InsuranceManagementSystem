package com.cts.service;

import com.cts.model.Policy;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PolicyService {

	Mono<Policy> save(Policy policy);

	Mono<Policy> get(Long id);

	Flux<Policy> getAll();

	Mono<Policy> update(Long id, Policy policy);
	
}
