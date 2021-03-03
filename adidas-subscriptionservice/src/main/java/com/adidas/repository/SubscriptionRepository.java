package com.adidas.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adidas.entity.Subscription;

@Repository("subscriptionRepository")
public interface SubscriptionRepository extends JpaRepository<Subscription, Serializable> {

	public abstract Subscription findById(int id);
	
}
