package com.cts.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cts.model.Claim;
import com.cts.repository.ClaimRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class ClaimServiceImpl implements ClaimService {

	private final ClaimRepository repository;
	private ClaimRepository repository2;
	@Override
	public Mono<Claim> save(Claim claim) {
		
		claim.setClaimId(null);
		return repository.save(claim);
	}
	@Override
	public Mono<ResponseEntity<String>> updateStatus(Long id, String status) {
	    return repository.findById(id)
	        .flatMap(claim -> {
	            claim.setStatus(status);
	            return repository.save(claim);
	        })
	        .map(updatedClaim -> ResponseEntity.ok("Status updated"))
	        .defaultIfEmpty(ResponseEntity.notFound().build());
	}
	@Override
	public Mono<Claim> get(Long id) {
		
		return repository.findById(id);
	}
	@Override
	public Flux<Claim> getAll() {
		return repository.findAll();
	}
	
	
}
