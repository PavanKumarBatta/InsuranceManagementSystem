package com.cts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.model.Claim;
import com.cts.service.ClaimService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/claim")
@RequiredArgsConstructor
public class ClaimController {
	
	private final ClaimService service;

	@PostMapping("/save")
	public Mono<ResponseEntity<Claim>> save(@RequestBody Claim claim) {
		
		
		return service.save(claim)
				.map(savedClaim->ResponseEntity.status(HttpStatus.CREATED).body(savedClaim));
	}
	
	@PutMapping("status/{id}/{status}")
	public Mono<ResponseEntity<String>> updateStatus(@PathVariable("id") Long id,@PathVariable("status") String status){
		
		return service.updateStatus(id,status);
	}
	
	@GetMapping("find/{id}")
	public Mono<Claim> get(@PathVariable Long id){
		
		return service.get(id);
	}
	
	@GetMapping("getAll")
	public Flux<Claim> getAll(){
		return service.getAll();
	}
}
