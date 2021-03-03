package com.adidas.feign.client;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.adidas.api.rest.request.AuthRequest;
import com.adidas.api.rest.request.RegistrationRequest;
import com.adidas.api.rest.response.AuthResponse;
import com.adidas.model.SubscriptionModel;
import com.adidas.utils.Constants;

import feign.Headers;

@FeignClient(name = "subscription-service")
@Headers("Content-Type: application/json")
public interface PublicRestClient {
		 	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request);
	
	@PostMapping("/register")
	public ResponseEntity<Void> registerUser(@RequestBody @Valid RegistrationRequest registrationRequest);
	
	@GetMapping("/app/subscriptions")
	@Headers("Content-Type: application/json")
	public ResponseEntity<List<SubscriptionModel>> getAllSubscriptions(@RequestHeader(Constants.AUTH_TOKEN) String bearerToken);	
	
	@GetMapping("/app/subscription")
	@Headers("Content-Type: application/json")
	public ResponseEntity<SubscriptionModel> getSubscription(@RequestHeader(Constants.AUTH_TOKEN) String bearerToken, @RequestParam(name="id", required=true) int id);
	
	@PostMapping("/app/subscription")
	@Headers("Content-Type: application/json")
	public ResponseEntity<Integer> addSubscription(@RequestHeader(Constants.AUTH_TOKEN) String bearerToken, @RequestBody SubscriptionModel subscriptionModel);
	
	@DeleteMapping("/app/subscription")
	@Headers("Content-Type: application/json")
	public ResponseEntity<Void> removeSubscription(@RequestHeader(Constants.AUTH_TOKEN) String bearerToken, @RequestParam(name="id", required=true) int id);
}
