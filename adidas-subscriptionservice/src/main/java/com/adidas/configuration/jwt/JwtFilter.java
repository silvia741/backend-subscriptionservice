package com.adidas.configuration.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import com.adidas.service.impl.CustomUserDetailsServiceImpl;

import lombok.extern.java.Log;

@Component
@Log
public class JwtFilter extends GenericFilterBean {

	private static final String AUTHORIZATION = "Authorization";

	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private CustomUserDetailsServiceImpl customUserDetailsService;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		logger.info("do filter...");
		String token = getTokenFromRequest((HttpServletRequest) request);
		if (token != null && jwtProvider.validateToken(token)) {
			String userLogin = jwtProvider.getLoginFromToken(token);
			CustomUserDetails customUserDetails = customUserDetailsService.loadUserByUsername(userLogin);
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		filterChain.doFilter(request, response);
	}
	
	public String getTokenFromRequest(HttpServletRequest request) {
		String bearer = request.getHeader(AUTHORIZATION);
		if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
			return bearer.substring(7);			
		}
		return null;
	}

}
