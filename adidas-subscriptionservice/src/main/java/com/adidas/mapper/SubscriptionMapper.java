package com.adidas.mapper;

import org.springframework.stereotype.Component;

import com.adidas.entity.Campaign;
import com.adidas.entity.Subscription;
import com.adidas.model.CampaignModel;
import com.adidas.model.SubscriptionModel;

@Component("subscriptionMapper")
public class SubscriptionMapper {
	
	private SubscriptionMapper() {}

	public static Subscription convertModelToEntity(SubscriptionModel subscriptionModel) {
		Subscription subscriptionEntity = new Subscription();
		subscriptionEntity.setDateOfBirth(subscriptionModel.getDateOfBirth());
		subscriptionEntity.setEmail(subscriptionModel.getEmail());
		subscriptionEntity.setFirstName(subscriptionModel.getFirstName());
		subscriptionEntity.setFlagForConsent(subscriptionModel.isFlagForConsent());
		subscriptionEntity.setGender(subscriptionModel.getGender());
		subscriptionEntity.setId(subscriptionModel.getId());
		Campaign campaign = new Campaign();
		CampaignModel campaignModel = subscriptionModel.getCampaignModel();
		if (campaignModel != null) {
			campaign.setNewsLetterId(campaignModel.getNewsLetterId());
			campaign.setName(campaignModel.getName());
			campaign.setDescription(campaignModel.getDescription());
		}
		subscriptionEntity.setCampaign(campaign);
		return subscriptionEntity;
	}
	
	public static SubscriptionModel convertEntityToModel(Subscription subscriptionEntity) {
		SubscriptionModel subscriptionModel = new SubscriptionModel();
		subscriptionModel.setDateOfBirth(subscriptionEntity.getDateOfBirth());
		subscriptionModel.setEmail(subscriptionEntity.getEmail());
		subscriptionModel.setFirstName(subscriptionEntity.getFirstName());
		subscriptionModel.setFlagForConsent(subscriptionEntity.isFlagForConsent());
		subscriptionModel.setGender(subscriptionEntity.getGender());
		subscriptionModel.setId(subscriptionEntity.getId());
		Campaign campaign = subscriptionEntity.getCampaign();
		CampaignModel campaignModel = new CampaignModel();
		if (campaign != null) {
			campaignModel.setDescription(campaign.getDescription());
			campaignModel.setNewsLetterId(campaign.getNewsLetterId());
			campaignModel.setName(campaign.getName());
		}
		subscriptionModel.setCampaignModel(campaignModel);
		return subscriptionModel;
	}
	
}
