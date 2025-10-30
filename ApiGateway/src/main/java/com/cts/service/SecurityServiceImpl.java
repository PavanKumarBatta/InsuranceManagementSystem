package com.cts.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.exception.BadRequestException;
import com.cts.exception.UserAlreadyExistsException;
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
		
		String name=user.getUserName();
		if(name==null) {
			throw new BadRequestException(400,"username should not be null");
		}
		if(user.getPassword()==null) {
			throw new BadRequestException(400,"Password should not be null");
		}
		if(user.getRole()==null) {
			throw new BadRequestException(400,"Role should not be empty");
		}
		boolean response=repo.existsByUserName(name);
		
		if(response) {
			
			throw new  UserAlreadyExistsException(400,"user with username already exists");
		}
		
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
						throw new BadRequestException(403,"Password is incorrect");
					}
				})
				.defaultIfEmpty("User not found");
	}
}
