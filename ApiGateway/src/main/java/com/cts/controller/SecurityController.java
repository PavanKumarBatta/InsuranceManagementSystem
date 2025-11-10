package com.cts.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.model.User;
import com.cts.model.UserLogin;
import com.cts.service.SecurityService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/apigateway")
@RequiredArgsConstructor
public class SecurityController {

	private final SecurityService service;
	
	@PostMapping("/register")
	public Mono<String> register(@RequestBody User user) {		
		return  service.register(user);
	}
	
	@PostMapping("/login")
	public Mono<String> login(@RequestBody UserLogin userLogin) {
		return service.login(userLogin);
	}
}
