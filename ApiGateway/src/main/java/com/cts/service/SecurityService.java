package com.cts.service;

import com.cts.model.User;
import com.cts.model.UserLogin;

import reactor.core.publisher.Mono;

public interface SecurityService {

	 Mono<String> register(User user);
	 Mono<String> login(UserLogin userLogin);
}
