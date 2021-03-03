package com.adidas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.adidas.entity.Role;
import com.adidas.entity.User;
import com.adidas.exception.SubscriptionException;
import com.adidas.repository.RoleRepository;
import com.adidas.repository.UserRepository;
import com.adidas.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User saveUser(User user) throws SubscriptionException {
		User userEntity = userRepository.findByLogin(user.getLogin());
		if (userEntity != null) {
			throw new SubscriptionException("User already exists");
		}
		Role userRole = roleRepository.findByName("ROLE_USER");
		user.setRole(userRole);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public User findByLogin(String login) {
		return userRepository.findByLogin(login);
	}

	@Override
	public User findByLoginAndPassword(String login, String password) {
		User userEntity = findByLogin(login);
		if (userEntity != null &&
			passwordEncoder.matches(password, userEntity.getPassword())) {
				return userEntity;
		}
		return null;
	}
	
	
	
}
