package com.adidas.api.rest.request;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class RegistrationRequest {

	@NotEmpty
	private String login;
	@NotEmpty
	private String password;
}
