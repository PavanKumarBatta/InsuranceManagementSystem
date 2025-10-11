package com.cts.service;

import org.springframework.http.ResponseEntity;

import com.cts.model.Claim;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClaimService {

	public Mono<Claim> save(Claim claim);

	public Mono<ResponseEntity<String>> updateStatus(Long id, String status);

	public Mono<Claim> get(Long id);

	public Flux<Claim> getAll();
}
