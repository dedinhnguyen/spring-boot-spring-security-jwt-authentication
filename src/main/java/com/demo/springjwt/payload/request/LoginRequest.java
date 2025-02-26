package com.demo.springjwt.payload.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class LoginRequest {
	@NotBlank
  private String username;

	@NotBlank
	private String password;
}
