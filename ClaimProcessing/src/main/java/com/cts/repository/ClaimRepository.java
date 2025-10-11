package com.cts.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.cts.model.Claim;

import reactor.core.publisher.Mono;

public interface ClaimRepository extends ReactiveCrudRepository<Claim,Long> {

	void save(Mono<Claim> claim);

}
