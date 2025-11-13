package com.cts.servicetest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cts.exception.BadRequestException;
import com.cts.model.User;
import com.cts.model.UserLogin;
import com.cts.repository.SecurityRepository;
import com.cts.service.SecurityServiceImpl;
import com.cts.utility.JwtUtil;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {

	@Mock
	private SecurityRepository repo;
	
	@Mock
    private BCryptPasswordEncoder encoder;
    
    @Mock
    private JwtUtil jwtUtil;
	
	@InjectMocks
	private SecurityServiceImpl service;
	
	@Test
	void testRegister_success() {
		
		//Arrange
		User user=new User(1L,"Pavan","Pavan@123","ADMIN");
		
		
		 when(repo.existsByUserName("Pavan")).thenReturn(false);
		 when(encoder.encode("Pavan@123")).thenReturn("encodedPwd");
		 when(repo.save(any(User.class))).thenReturn(Mono.just(user));
		//Act
		Mono<String>response=service.register(user);
		//Assert
		StepVerifier.create(response)
		.expectNext("Registration Successful")
		.verifyComplete();
		
//		verify(repo,times(1)).save(user);
	}
	@Test
	void testRegister_failure1() {
	    // Arrange
	    User user = new User(1L, null, "Pavan@123", "ADMIN");

	    // Act
	    Mono<String> response = service.register(user);

	    // Assert
	    StepVerifier.create(response)
	            .expectErrorMatches(t ->
	                    t instanceof BadRequestException &&
	                    t.getMessage().contains("username shouldnot be null"))
	            		.verify();
	}
	
	@Test
	void testRegister_failure2() {
		//Arrange
		User user=new User(1L,"Pavan",null,"ADMIN");
		//Act
		Mono<String> response=service.register(user);
		//Assert
		StepVerifier.create(response).expectErrorMatches(t->
				t instanceof BadRequestException &&
				t.getMessage().contains("Password shouldnot be null"))
				.verify();
	}
	
	@Test
	void testRegister_failure3() {
		//Arrange
		User user=new User(1L,"Pavan","Pavan@123",null);
		//Act
		Mono<String>response=service.register(user);
		//Assert
		StepVerifier.create(response).expectErrorMatches(t->
			t instanceof BadRequestException &&
			t.getMessage().contains("Role shouldnot be empty"))
			.verify();
	}
	
	@Test
	void testLogin() {
		//Arrange
		UserLogin userLogin=new UserLogin("Pavan","Pavan@123","ADMIN");
		User mockUser = User.builder()
	            .userName("Pavan")
	            .password(new BCryptPasswordEncoder().encode("Pavan@123"))
	            .role("ADMIN")
	            .build();
		 String token = "fixedToken123";
		when(repo.findByUserName(userLogin.getUserName())).thenReturn(Mono.just(mockUser));
		when(jwtUtil.generateToken(userLogin.getUserName(), userLogin.getRole())).thenReturn(token);
		//Act
		Mono<String>response=service.login(userLogin);
		
		//Assert
		StepVerifier.create(response)
		.expectNext(token)
		.verifyComplete();
	}
}
