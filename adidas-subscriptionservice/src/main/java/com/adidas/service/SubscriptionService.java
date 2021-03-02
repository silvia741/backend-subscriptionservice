package com.adidas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.adidas.exception.SubscriptionException;
import com.adidas.model.SubscriptionModel;

@Service
public interface SubscriptionService {

	public abstract SubscriptionModel addSubscription(SubscriptionModel subscriptionModel) throws Exception;
	
	public abstract List<SubscriptionModel> listAllSubscriptions();
	
	public abstract SubscriptionModel findSubscriptionById(int id);
	
	public abstract void removeSubscription(int id) throws SubscriptionException;
		
}
