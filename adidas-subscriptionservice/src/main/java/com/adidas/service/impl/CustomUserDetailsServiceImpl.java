package com.adidas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.adidas.configuration.jwt.CustomUserDetails;
import com.adidas.entity.User;

@Component
public class CustomUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserServiceImpl userService;
	
	@Override
	public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userEntity = userService.findByLogin(username);
		
		return CustomUserDetails.fromUserEntityToCustomerDetails(userEntity);
	}

}
