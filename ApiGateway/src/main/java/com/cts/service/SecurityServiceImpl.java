package com.cts.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.exception.BadRequestException;
import com.cts.exception.UserAlreadyExistsException;
import com.cts.model.User;
import com.cts.model.UserLogin;
import com.cts.repository.SecurityRepository;
import com.cts.utility.JwtUtil;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

	private final SecurityRepository repo;
	private final BCryptPasswordEncoder encoder;
	private final JwtUtil jwtUtil;
	@Override
	public Mono<String> register(User user) {
		
		String name=user.getUserName();
		if(name==null) {
			return Mono.error(new BadRequestException(400,"username shouldnot be null"));
		}
		if(user.getPassword()==null) {
			return Mono.error(new BadRequestException(400,"Password shouldnot be null"));
		}
		if(user.getRole()==null) {
			return Mono.error( new BadRequestException(400,"Role shouldnot be empty"));
		}
		
		boolean response=repo.existsByUserName(name);
		
		if(response) {
			return Mono.error(new  UserAlreadyExistsException(400,"user with username already exists"));
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
						return jwtUtil.generateToken(userLogin.getUserName(),userLogin.getRole());							
					}else {
						throw new BadRequestException(403,"Password is incorrect");
					}
				})
				.defaultIfEmpty("User not found");
	}
}
