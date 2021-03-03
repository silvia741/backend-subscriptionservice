package com.adidas.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.exception.SubscriptionException;
import com.adidas.model.SubscriptionModel;
import com.adidas.service.SubscriptionService;

@RestController
@RequestMapping("/app")
public class SubscriptionController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
		
	@Autowired
	@Qualifier("subscriptionServiceImpl")
	private SubscriptionService subscriptionService;
	
	/**
	 * Get all subscriptions
	 * @return
	 */
	@GetMapping("/subscriptions")
	public ResponseEntity<List<SubscriptionModel>> getAllSubscriptions() {
		List<SubscriptionModel> subscriptionModelList = null;
		logger.info("Starting rest service SubscriptionController.getAllSubscriptions");
		ResponseEntity<List<SubscriptionModel>> response = null;
		try {			
			subscriptionModelList = subscriptionService.listAllSubscriptions();
			if (subscriptionModelList != null) {
				response = ResponseEntity.ok(subscriptionModelList);
			}
		} catch (Exception e) {
			logger.error("Uncontrolled error while getting all subscriptions", e);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {
			logger.info("Ending rest service SubscriptionController.getAllSubscriptions");
		}
		return response;
	}
	
	/**
	 * Get a subscription by id
	 * @param id
	 * @return
	 */
	@GetMapping("/subscription")
	public ResponseEntity<SubscriptionModel> getSubscription(@RequestParam(name="id", required=true) int id) {
		logger.info("Starting rest service SubscriptionController.getSubscription");
		ResponseEntity<SubscriptionModel> response = null;
		SubscriptionModel subscriptionModel = null;
		try {			
			subscriptionModel = subscriptionService.findSubscriptionById(id);
			if (subscriptionModel != null) {
				response = ResponseEntity.ok(subscriptionModel);
			} else {
				response = ResponseEntity.notFound().build();
			}		
		} catch (Exception e) {
			logger.error(String.format("Uncontrolled error while getting subscription [%d]", id), e);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {
			logger.info("Ending rest service SubscriptionController.getSubscription");
		}
		return response;
	}
	
	/**
	 * Add a subscription
	 * @param subscriptionModel
	 * @param model
	 * @return
	 */
	@PostMapping("/subscription")
	public ResponseEntity<Integer> addSubscription(@RequestBody SubscriptionModel subscriptionModel) {
		logger.info("Starting rest service SubscriptionController.addSubscription");
		ResponseEntity<Integer> response = null;
		try {			
			subscriptionService.addSubscription(subscriptionModel);
			response = ResponseEntity.ok(subscriptionModel.getId());
			
		} catch (DataIntegrityViolationException de) {
			logger.error("The subscription already exists, cannot create again", de);
			response = ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (Exception e) {
			logger.error("Uncontrolled error while creating subscription", e);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {
			logger.info("Ending rest service SubscriptionController.addSubscription");
		}		
		return response;
	}
	
	/**
	 * Removes subscription
	 * @param subscritpionId
	 * @return
	 */
	@DeleteMapping("/subscription")
	public ResponseEntity<Void> removeSubscription(@RequestParam(name="id", required=true) int id) {
		logger.info("Starting rest service SubscriptionController.removeSubscription");
		ResponseEntity<Void> response = null;
		try {			
			subscriptionService.removeSubscription(id);
			response = ResponseEntity.ok().build();
		} catch (SubscriptionException se) {
			logger.error(String.format("Subscription to remove with id [%d] not found", id), se);
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			logger.error("Uncontrolled error while removing subscription", e);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} finally {
			logger.info("Ending rest service SubscriptionController.removeSubscription");
		}		
		return response;
	}
	
}
