package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.AccessToken;

public interface AuthTokenRepository extends JpaRepository<AccessToken, Long>{
	
	
	public AccessToken findByToken(String userToken);
	

}
