package com.adidas.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.model.SubscriptionModel;

@RestController
@RequestMapping("/subcriptions")
public class SubscriptionController {

	@GetMapping("/subscriptions")
	public String getAllSubscriptions() {
		return "";
	}
	
	@GetMapping("/subscription/{subscriptionId}")
	public String getSubscription(@PathVariable("subscritpionId") int subscritpionId) {
		return "";
	}
	
	@PostMapping("/subscription}")
	public String addSubscription(@ModelAttribute(name="subscriptionModel") SubscriptionModel subscriptionModel,
			Model model) {
		return "";
	}
	
	@DeleteMapping("/subscription/{subscritpionId}")
	public String addSubscription(@PathVariable("subscritpionId") int subscritpionId) {
		return "";
	}
	
}
