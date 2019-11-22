package com.app.request;

import javax.validation.constraints.NotNull;

public class RegisterDto {

	@NotNull(message ="Users Email Cannot be NULL")
	private String email;

	@NotNull(message ="Full Name Cannot be NULL")
	private String fullName;

	@NotNull(message ="Users Password Cannot be NULL")
	private String password;
	
	public RegisterDto() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
