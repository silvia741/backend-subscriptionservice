package com.adidas.api.rest.request;

import lombok.Data;

@Data
public class AuthRequest {

	private String login;
	private String password;
}
