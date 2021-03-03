package com.adidas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.adidas.model.SubscriptionModel;


@RestController
public class EmailNotificationController {
	
	@GetMapping("/email")
	public ModelAndView sendEmail(@RequestBody SubscriptionModel subscriptionModel) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("username", subscriptionModel.getFirstName());
		mav.setViewName("emailnotification");
		return mav;
	}
	
}
