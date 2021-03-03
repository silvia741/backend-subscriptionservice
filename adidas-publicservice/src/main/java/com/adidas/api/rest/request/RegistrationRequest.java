package com.adidas.api.rest.request;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@Getter
@AllArgsConstructor
public class RegistrationRequest {

	@NotEmpty
	private String login;
	@NotEmpty
	private String password;
}
