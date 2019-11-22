package com.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class AccessToken extends IbSupport{

	@Id
	@GeneratedValue(generator = "access_token_generator")
	@SequenceGenerator(
			name = "access_token_generator",
			sequenceName = "access_token_generator",
			initialValue = 1
			)	
	private long id;

	private String token;

	@ManyToOne
	@JoinColumn
	private Users users;

	public AccessToken() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Users getUser() {
		return users;
	}

	public void setUser(Users users) {
		this.users = users;
	}
	

	
	
	
}
