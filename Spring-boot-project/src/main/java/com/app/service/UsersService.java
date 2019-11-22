package com.app.service;

import com.app.customexception.AlreadyExistException;
import com.app.customexception.NotFoundException;
import com.app.model.Users;
import com.app.request.RegisterDto;


public interface UsersService {
	// new 
	public Users signUp(RegisterDto registerDto) throws AlreadyExistException;

	public Users signIn(String email) throws NotFoundException;
	
}
