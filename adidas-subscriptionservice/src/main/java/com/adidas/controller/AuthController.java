package com.adidas.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.api.rest.request.AuthRequest;
import com.adidas.api.rest.request.AuthResponse;
import com.adidas.api.rest.request.RegistrationRequest;
import com.adidas.configuration.jwt.JwtProvider;
import com.adidas.entity.User;
import com.adidas.exception.SubscriptionException;
import com.adidas.service.impl.UserServiceImpl;

@RestController
public class AuthController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private JwtProvider jwtProvider;
	
	@PostMapping("/register")
	public ResponseEntity<Void> registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
		logger.info("Starting rest service AuthController.registerUser");
		ResponseEntity<Void> response = null;
		try {	
			User user = new User();
			user.setPassword(registrationRequest.getPassword());
			user.setLogin(registrationRequest.getLogin());
			userService.saveUser(user);
			response = ResponseEntity.ok().build();
		} catch (SubscriptionException se) {
			logger.error("User already exists", se);
			response = ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (Exception e) {
			logger.error("Uncontrolled error while creating new user", e);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {
			logger.info("Ending rest service AuthController.registerUser");
		}
		return response;
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
		logger.info("Starting rest service AuthController.login");
		ResponseEntity<AuthResponse> response = null;
		AuthResponse authResponse = null;
		User userEntity = null;
		String token = null;
		try {
			userEntity = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
			token = jwtProvider.generateToken(userEntity.getLogin());
			authResponse = new AuthResponse(token);
			response = ResponseEntity.ok(authResponse);
		} catch (Exception e) {
			logger.error("Uncontrolled error in login", e);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {
			logger.info("Ending rest service AuthController.login");
		}
		return response;
	}
}
