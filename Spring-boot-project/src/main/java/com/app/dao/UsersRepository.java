package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{
	
	public Users findByEmail(String email);

}
