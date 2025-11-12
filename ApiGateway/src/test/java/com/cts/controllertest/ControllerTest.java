package com.cts.controllertest;

import static org.mockito.ArgumentMatchers.any;
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
import com.cts.model.UserLogin;
import com.cts.service.SecurityService;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class ControllerTest {

    @Mock
    private SecurityService service;

    @InjectMocks
    private SecurityController controller;

    User validUser;

    @BeforeEach
    void setup() {
        validUser = new User(1L, "Pavan", "Pavan@123", "ADMIN");
    }

    @Test
    void testUserRegister_success() {
        // Arrange
        when(service.register(validUser)).thenReturn(Mono.just("Registration Successful"));

        // Act
        Mono<String> response = controller.register(validUser);

        // Assert
        StepVerifier.create(response)
                .expectNext("Registration Successful")
                .verifyComplete();

        verify(service, times(1)).register(validUser);
    }

    @Test
    void testUserRegister_failure1() {
        // Arrange
        User invalidUser = new User(1L, null, "Pavan@123", "ADMIN");
        when(service.register(any(User.class)))
                .thenReturn(Mono.error(new BadRequestException(400, "username shouldnot be null")));

        // Act
        Mono<String> response = controller.register(invalidUser);

        // Assert
        StepVerifier.create(response)
                .expectErrorMatches(throwable ->
                        throwable instanceof BadRequestException &&
                        throwable.getMessage().contains("username shouldnot be null"))
                .verify();

        verify(service, times(1)).register(invalidUser);
    }

    @Test
    void testUserRegister_failure2() {
        // Arrange
        User invalidUser = new User(1L, "Pavan", null, "ADMIN");
        when(service.register(any(User.class)))
                .thenReturn(Mono.error(new BadRequestException(400, "Password shouldnot be null")));

        // Act
        Mono<String> response = controller.register(invalidUser);

        // Assert
        StepVerifier.create(response)
                .expectErrorMatches(throwable ->
                        throwable instanceof BadRequestException &&
                        throwable.getMessage().contains("Password shouldnot be null"))
                .verify();

        verify(service, times(1)).register(invalidUser);
    }
    
    @Test
    void testUserRegister_failure3() {
    	
    	//Arrange
    	User invalidUser=new User(1L,"Pavan","Pavan@123",null);
    	when(service.register(invalidUser)).thenReturn(Mono.error(new BadRequestException(400,"Role shouldnot be null")));
    	
    	//Act
    	Mono<String>response=controller.register(invalidUser);
    	
    	//Assert
    	StepVerifier.create(response)
    	.expectErrorMatches(throwable->throwable instanceof BadRequestException && 
    			throwable.getMessage().contains("Role shouldnot be null"))
    	.verify();
    	
    	verify(service,times(1)).register(invalidUser);
    }
    
    @Test
    void testLogin() {
    	
    	//Arrange
    	UserLogin userLogin=new UserLogin("Pavan","Pavan@123","ADMIN");
    	when(service.login(userLogin)).thenReturn(Mono.just("Login Successful"));
    	//Act
    	Mono<String> response=controller.login(userLogin);
    	//Assert
    	StepVerifier.create(response)
    	.expectNext("Login Successful")
    	.verifyComplete();
    	
    	verify(service,times(1)).login(userLogin);
    }
    
    @Test
    void testLogin_passwordIncorrect() throws BadRequestException {
    	
    	//Arrange
    	UserLogin userLogin=new UserLogin("Pavan","asdfg","ADMIN");
    	when(service.login(userLogin)).thenReturn(Mono.error(new BadRequestException(400,"Password incorrect")));
    	
    	//Act
    	Mono<String>response=controller.login(userLogin);
 
    	//Assert
    	
    	StepVerifier.create(response)
    	.expectErrorMatches(throwable->throwable instanceof BadRequestException && 
    			throwable.getMessage().contains("Password incorrect"))
    	.verify();
    	
    }
}
