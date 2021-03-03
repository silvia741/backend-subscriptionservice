package com.adidas.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.api.rest.request.AuthRequest;
import com.adidas.api.rest.request.RegistrationRequest;
import com.adidas.api.rest.response.AuthResponse;
import com.adidas.feign.client.PublicRestClient;
import com.adidas.model.SubscriptionModel;
import com.adidas.utils.Constants;

import feign.FeignException;
import feign.Headers;

@RestController
@RequestMapping("/v2")
public class PublicController {

	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private PublicRestClient publicRestClient;
		
	@Autowired
	public PublicController(PublicRestClient publicRestClient) {
		this.publicRestClient = publicRestClient;
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) { 
		logger.info("Starting rest service PublicController.login");
		ResponseEntity<AuthResponse> response = null;
		try {
			response = publicRestClient.login(request);
		} catch (FeignException fe) {
			logger.error("Error in login process", fe);
			response = ResponseEntity.status(fe.status()).build();
		} catch (Exception e) {
			logger.error("Uncontrolled error in login", e);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {
			logger.info("Ending rest service PublicController.login");
		}
		return response;
	}
	
	@PostMapping("/register")
	public ResponseEntity<Void> registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
		logger.info("Starting rest service PublicController.registerUser");
		ResponseEntity<Void> response = null;
		try {	
			response = publicRestClient.registerUser(registrationRequest);
		} catch (FeignException fe) {
			logger.error("Error in register process", fe);
			response = ResponseEntity.status(fe.status()).build();
		} catch (Exception e) {
			logger.error("Uncontrolled error while creating new user", e);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {
			logger.info("Ending rest service PublicController.registerUser");
		}
		return response;
	}
	
	@GetMapping("/subscriptions")
	@Headers("Content-Type: application/json")
	public ResponseEntity<List<SubscriptionModel>> getAllSubscriptions(@RequestHeader(Constants.AUTH_TOKEN) String bearerToken){
		logger.info("Starting rest service PublicController.getAllSubscriptions");
		ResponseEntity<List<SubscriptionModel>> response = null;
		try {			
			response = publicRestClient.getAllSubscriptions(bearerToken);			
		} catch (FeignException fe) {
			logger.error("Error while getting all subscriptions", fe);
			response = ResponseEntity.status(fe.status()).build();
		} catch (Exception e) {
			logger.error("Uncontrolled error while getting all subscriptions", e);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {
			logger.info("Ending rest service PublicController.getAllSubscriptions");
		}
		return response;
	}
	
	@GetMapping("/subscription")
	@Headers("Content-Type: application/json")
	public ResponseEntity<SubscriptionModel> getSubscription(@RequestHeader(Constants.AUTH_TOKEN) String bearerToken, @RequestParam(name="id", required=true) int id) {
		logger.info("Starting rest service PublicController.getSubscription");
		ResponseEntity<SubscriptionModel> response = null;
		try {			
			response = publicRestClient.getSubscription(bearerToken, id);
			
		} catch (FeignException fe) {
			logger.error(String.format("Error while getting subscription [%d]", id), fe);
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
		} catch (Exception e) {
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {
			logger.info("Ending rest service PublicController.getSubscription");
		}
		return response;
	}
	
	@PostMapping("/subscription")
	@Headers("Content-Type: application/json")
	public ResponseEntity<Integer> addSubscription(@RequestHeader(Constants.AUTH_TOKEN) String bearerToken, @RequestBody SubscriptionModel subscriptionModel) {
		logger.info("Starting rest service PublicController.addSubscription");
		ResponseEntity<Integer> response = null;
		try {			
			response = publicRestClient.addSubscription(bearerToken, subscriptionModel);			
		} catch (FeignException fe) {
			logger.error("Error while creating a new subscription", fe);
			response = ResponseEntity.status(fe.status()).build();
		} catch (Exception e) {
			logger.error("Uncontrolled error while creating subscription", e);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {
			logger.info("Ending rest service PublicController.addSubscription");
		}		
		return response;
	}

	@DeleteMapping("/subscription")
	@Headers("Content-Type: application/json")
	public ResponseEntity<Void> removeSubscription(@RequestHeader(Constants.AUTH_TOKEN) String bearerToken, @RequestParam(name="id", required=true) int id) {
		logger.info("Starting rest service PublicController.removeSubscription");
		ResponseEntity<Void> response = null;
		try {			
			response = publicRestClient.removeSubscription(bearerToken, id);
		} catch (FeignException fe) {
			logger.error(String.format("Error while removing subscription [%d]", id), fe);
			response = ResponseEntity.status(fe.status()).build();
		} catch (Exception e) {
			logger.error("Uncontrolled error while removing subscription", e);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {
			logger.info("Ending rest service PublicController.removeSubscription");
		}
		return response;
	}
}
