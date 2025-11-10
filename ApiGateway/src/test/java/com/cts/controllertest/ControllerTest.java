package com.cts.controllertest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cts.controller.SecurityController;
import com.cts.exception.BadRequestException;
import com.cts.model.User;
import com.cts.service.SecurityService;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class ControllerTest {

	
	@Mock
	private SecurityService service;
	
	@InjectMocks
	private SecurityController controller;
	
	User user;
	
	@BeforeEach
	void setup() {
		
		user=new User((long) 1,"Pavan","Pavan@123","ADMIN");
	}
	
	@Test
	void testUserRegister_success() {
		
		//Arrange
		when(service.register(user)).thenReturn(Mono.just("Registration Successful"));	
		//Act
		Mono<String> response=controller.register(user);
		//Assert
		StepVerifier.create(response)
        .expectNext("Registration Successful")
        .verifyComplete();
		
		
		//assertEquals(response,"Registration Successful");
		//assert fails in reactive programming.
		
		verify(service,times(1)).register(user);
	}
	
	@Test
	void testUserRegister_failure1() throws BadRequestException {
		
		//Arrange
		User invalidUser=new User(1L,null,"Pavan@123","ADMIN");
		//Act
		Mono<String>response=controller.register(invalidUser);
		//Assert
		StepVerifier.create(response)
		.expectErrorMatches(throwable->throwable instanceof BadRequestException && throwable.getMessage().contains("username should not be null"))
		.verify();
	}
	
	@Test
	void testUserRegister_failure2() throws BadRequestException{
		
		//Arrange
		User invalidUser=new User(1L,"Pavan",null,"ADMIN");
		//Act
		Mono<String>response=controller.register(invalidUser);
		//Assert
		StepVerifier.create(response)
		.expectErrorMatches(throwable->throwable instanceof BadRequestException && throwable.getMessage().contains("Password should not be null"))
		.verify();
	}
}