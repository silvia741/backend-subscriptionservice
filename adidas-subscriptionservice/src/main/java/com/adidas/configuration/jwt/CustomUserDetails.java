package com.adidas.configuration.jwt;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.adidas.entity.User;

public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = -9106045277824047388L;
	
	private String login;
	private String password;
	private Collection<? extends GrantedAuthority> grantedAuthorities;
	
	public static CustomUserDetails fromUserEntityToCustomerDetails(User user) {
		CustomUserDetails customUserDetails = new CustomUserDetails();
		customUserDetails.login = user.getLogin();
		customUserDetails.password = user.getPassword();
		customUserDetails.grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getName()));
		return customUserDetails;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
