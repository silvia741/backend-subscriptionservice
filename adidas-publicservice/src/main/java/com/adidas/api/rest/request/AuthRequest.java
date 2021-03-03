package com.adidas.api.rest.request;

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
public class AuthRequest {

	private String login;
	private String password;
}
