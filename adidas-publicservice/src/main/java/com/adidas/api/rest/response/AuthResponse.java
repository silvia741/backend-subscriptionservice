package com.adidas.api.rest.response;

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
public class AuthResponse {

	private String token;
}
