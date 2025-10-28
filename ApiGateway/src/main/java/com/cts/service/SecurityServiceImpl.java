package com.cts.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.model.User;
import com.cts.model.UserLogin;
import com.cts.repository.SecurityRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

	private final SecurityRepository repo;
	private final BCryptPasswordEncoder encoder;
	@Override
	public Mono<String> register(User user) {
		
		User newUser=user.toBuilder()
				.id(null)
				.role(user.getRole().toUpperCase())
				.password(encoder.encode(user.getPassword()))
				.build();
		 return repo.save(newUser)
			        .thenReturn("Registration Successful");
	}
	@Override
	public Mono<String> login(UserLogin userLogin) {
		
		return repo.findByUserName(userLogin.getUserName())
				.map(user->{
					if(encoder.matches(userLogin.getPassword(), user.getPassword())) {
						return "Login Successfull";							
					}else {
						return "Login Unsuccessful";
					}
				})
				.defaultIfEmpty("User not found");
	}
}
