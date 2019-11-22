package com.app.request;

import javax.validation.constraints.NotNull;

public class LoginRequest {
	
	@NotNull(message ="Users Email Cannot be NULL")
	private String email;
	
	@NotNull(message ="Users Password Cannot be NULL")
	private String password;

	public LoginRequest() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
