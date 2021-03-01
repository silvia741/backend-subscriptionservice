package com.adidas.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.adidas.entity.Subscription;
import com.adidas.exception.SubscriptionException;
import com.adidas.mapper.SubscriptionMapper;
import com.adidas.model.SubscriptionModel;
import com.adidas.repository.SubscriptionRepository;
import com.adidas.service.SubscriptionService;

@Service("subscriptionServiceImpl")
public class SubscriptionServiceImpl implements SubscriptionService {

	@Autowired
	@Qualifier("subscriptionRepository")
	private SubscriptionRepository subscriptionRepository;
	
	@Autowired
	@Qualifier("subscriptionMapper")
	private SubscriptionMapper subscriptionMapper;
		
	@Override
	public SubscriptionModel findSubscriptionById(int id) {
		SubscriptionModel subscriptionModel = null;
		Subscription subscriptionEntity = subscriptionRepository.findById(id);
		if (subscriptionEntity != null) {
			subscriptionModel = subscriptionMapper.convertEntityToModel(subscriptionEntity);
		}
		return subscriptionModel;
	}

	@Override
	public SubscriptionModel addSubscription(SubscriptionModel subscriptionModel) throws Exception {
		Subscription subscription = subscriptionRepository.save(subscriptionMapper.convertModelToEntity(subscriptionModel));
		return subscriptionMapper.convertEntityToModel(subscription);
	}

	@Override
	public List<SubscriptionModel> listAllSubscriptions() {
		List<Subscription> subscriptionList = subscriptionRepository.findAll();
		List<SubscriptionModel> subscriptionModelList = new ArrayList<>();
		for (Subscription subscription : subscriptionList) {
			subscriptionModelList.add(subscriptionMapper.convertEntityToModel(subscription));
		}
		return subscriptionModelList;
	}

	@Override
	public void removeSubscription(int id) throws SubscriptionException {
		Subscription subscription = subscriptionRepository.findById(id);
		if (subscription != null) {
			subscriptionRepository.delete(subscription);
		} else {
			throw new SubscriptionException("Subscription not found");
		}
	}
}
