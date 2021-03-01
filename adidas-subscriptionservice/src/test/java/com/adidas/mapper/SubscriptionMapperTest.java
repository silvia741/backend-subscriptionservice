package com.adidas.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.adidas.entity.Campaign;
import com.adidas.entity.Subscription;
import com.adidas.model.CampaignModel;
import com.adidas.model.SubscriptionModel;

@RunWith(JUnitPlatform.class)
public class SubscriptionMapperTest {

	private Subscription subscription;
	private Campaign campaign;
	private SubscriptionModel subscriptionModel;
	
	@BeforeEach
	public void init() {
		subscription = new Subscription();
		campaign = new Campaign();
		campaign.setDescription("description");
		campaign.setName("name");
		campaign.setNewsLetterId(1);
		subscription.setCampaign(campaign);
		subscription.setDateOfBirth(null);
		subscription.setEmail("a@gmail.com");
		subscription.setFirstName("firstName");
		subscription.setFlagForConsent(true);
		subscription.setGender("female");
		subscription.setId(1);
		
		subscriptionModel = new SubscriptionModel();
		CampaignModel campaignModel = new CampaignModel();
		campaignModel.setDescription(campaign.getDescription());
		campaignModel.setName(campaign.getName());
		campaignModel.setNewsLetterId(campaign.getNewsLetterId());
		subscriptionModel.setCampaignModel(campaignModel);
		subscriptionModel.setDateOfBirth(subscription.getDateOfBirth());
		subscriptionModel.setEmail(subscription.getEmail());
		subscriptionModel.setFirstName(subscription.getFirstName());
		subscriptionModel.setFlagForConsent(subscription.isFlagForConsent());
		subscriptionModel.setGender(subscription.getGender());
		subscriptionModel.setId(subscription.getId());
	}
	
	@Test
	public void testSubscriptionEntityToModel() {
		SubscriptionModel mappedSubscriptionModel = SubscriptionMapper.convertEntityToModel(subscription);
		assertNotNull(subscriptionModel, "Unexpected null entity");
		assertEquals("Unexpected field value", subscriptionModel.getCampaignModel().getDescription(), mappedSubscriptionModel.getCampaignModel().getDescription());
		assertEquals("Unexpected field value", subscriptionModel.getCampaignModel().getName(), mappedSubscriptionModel.getCampaignModel().getName());
		assertEquals("Unexpected field value", subscriptionModel.getCampaignModel().getNewsLetterId(), mappedSubscriptionModel.getCampaignModel().getNewsLetterId());
		assertEquals("Unexpected field value", subscriptionModel.getEmail(), mappedSubscriptionModel.getEmail());
		assertEquals("Unexpected field value", subscriptionModel.getGender(), mappedSubscriptionModel.getGender());
		assertEquals("Unexpected field value", subscriptionModel.getFirstName(), mappedSubscriptionModel.getFirstName());
		assertEquals("Unexpected field value", subscriptionModel.getId(), mappedSubscriptionModel.getId());
		assertEquals("Unexpected field value", subscriptionModel.getDateOfBirth(), mappedSubscriptionModel.getDateOfBirth());	
	}
	
	@Test
	public void testSubscriptionModelToEntity() {
		Subscription mappedSubscription = SubscriptionMapper.convertModelToEntity(subscriptionModel);
		assertNotNull(subscription, "Unexpected null entity");
		assertEquals("Unexpected field value", subscription.getCampaign().getDescription(), mappedSubscription.getCampaign().getDescription());
		assertEquals("Unexpected field value", subscription.getCampaign().getName(), mappedSubscription.getCampaign().getName());
		assertEquals("Unexpected field value", subscription.getCampaign().getNewsLetterId(), mappedSubscription.getCampaign().getNewsLetterId());
		assertEquals("Unexpected field value", subscription.getEmail(), mappedSubscription.getEmail());
		assertEquals("Unexpected field value", subscription.getGender(), mappedSubscription.getGender());
		assertEquals("Unexpected field value", subscription.getFirstName(), mappedSubscription.getFirstName());
		assertEquals("Unexpected field value", subscription.getId(), mappedSubscription.getId());
		assertEquals("Unexpected field value", subscription.getDateOfBirth(), mappedSubscription.getDateOfBirth());	
	}
	
}
