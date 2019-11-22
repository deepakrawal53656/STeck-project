package com.app.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.customexception.AlreadyExistException;
import com.app.customexception.NotFoundException;
import com.app.dao.UsersRepository;
import com.app.model.Users;
import com.app.request.RegisterDto;
import com.app.service.UsersService;

@Service("usersService")
public class UsersServiceImpl implements UsersService{
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Users signUp(RegisterDto registerDto) throws AlreadyExistException {
		
		Users users = null;
			
		users = usersRepository.findByEmail(registerDto.getEmail());
		
		if(users != null) {
			throw new AlreadyExistException("This email address already exists");
		}

		
		users = new Users();
		users.setEmail(registerDto.getEmail());
		users.setFullName(registerDto.getFullName());
		users.setPassword(bCryptPasswordEncoder.encode(registerDto.getPassword()));
		
		return usersRepository.save(users);
	}
	
	@Override
	public Users signIn(String email) throws NotFoundException {
	
		Users users = usersRepository.findByEmail(email);
		if (users == null) {
			throw new NotFoundException("Users not found");
		}	
		
		return users;
	}	
	
	
	

}
