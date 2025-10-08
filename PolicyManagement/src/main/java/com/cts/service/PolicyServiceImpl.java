package com.cts.service;

import org.springframework.stereotype.Service;

import com.cts.model.Policy;
import com.cts.repository.PolicyRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PolicyServiceImpl implements PolicyService{
	
	private final PolicyRepository repository;
	
	public PolicyServiceImpl(PolicyRepository repository) {
		this.repository=repository;
	}
	
	@Override
	public Mono<Policy> save(Policy policy) {
		
		policy.setPolicyId(null);
		return repository.save(policy);
	}

	@Override
	public Mono<Policy> get(Long id) {
		
		return repository.findById(id);
	}

	@Override
	public Flux<Policy> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Mono<Policy> update(Long id, Policy policy) {
		// TODO Auto-generated method stub
		Policy updatePolicy=Policy.builder()
				.policyId(id)
				.name(policy.getName())
				.premiumAmount(policy.getPremiumAmount())
				.coverageDetails(policy.getCoverageDetails())
				.validityPeriod(policy.getValidityPeriod())
				.build();
		return repository.save(updatePolicy);
	}

}
