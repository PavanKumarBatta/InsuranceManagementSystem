package com.cts.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.cts.model.User;

import reactor.core.publisher.Mono;

public interface SecurityRepository extends ReactiveCrudRepository<User,Long> {

	Mono<User> findByUserName(String userName);	
	
	boolean existsByUserName(String userName);
	
}
