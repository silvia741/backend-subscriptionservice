package com.adidas.mapper;

import org.springframework.stereotype.Component;

import com.adidas.entity.Subscription;
import com.adidas.model.SubscriptionModel;

@Component("subscriptionMapper")
public class SubscriptionMapper {

	public Subscription convertModelToEntity(SubscriptionModel subscriptionModel) {
		Subscription subscriptionEntity = new Subscription();
		subscriptionEntity.setDateOfBirth(subscriptionModel.getDateOfBirth());
		subscriptionEntity.setEmail(subscriptionModel.getEmail());
		subscriptionEntity.setFirstName(subscriptionModel.getFirstName());
		subscriptionEntity.setFlagForConsent(subscriptionModel.isFlagForConsent());
		subscriptionEntity.setGender(subscriptionModel.getGender());
		subscriptionEntity.setId(subscriptionModel.getId());
		subscriptionEntity.setNewsletterId(subscriptionModel.getNewsletterId());
		return subscriptionEntity;
	}
	
	public SubscriptionModel convertEntityToModel(Subscription subscriptionEntity) {
		SubscriptionModel subscriptionModel = new SubscriptionModel();
		subscriptionModel.setDateOfBirth(subscriptionEntity.getDateOfBirth());
		subscriptionModel.setEmail(subscriptionEntity.getEmail());
		subscriptionModel.setFirstName(subscriptionEntity.getFirstName());
		subscriptionModel.setFlagForConsent(subscriptionEntity.isFlagForConsent());
		subscriptionModel.setGender(subscriptionEntity.getGender());
		subscriptionModel.setId(subscriptionEntity.getId());
		subscriptionModel.setNewsletterId(subscriptionEntity.getNewsletterId());
		return subscriptionModel;
	}
	
}
