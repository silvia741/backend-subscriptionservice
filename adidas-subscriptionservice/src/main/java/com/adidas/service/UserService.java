package com.adidas.service;

import org.springframework.stereotype.Service;

import com.adidas.entity.User;
import com.adidas.exception.SubscriptionException;

@Service
public interface UserService {

	public User saveUser(User user) throws SubscriptionException;
	
	public User findByLogin(String login);
	
	public User findByLoginAndPassword(String login, String password);
}
