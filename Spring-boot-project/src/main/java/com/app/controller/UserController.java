package com.app.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Oauth.SecurityConstants;
import com.app.config.StaticMessages;
import com.app.customexception.AlreadyExistException;
import com.app.customexception.NotFoundException;
import com.app.model.AccessToken;
import com.app.model.Users;
import com.app.request.LoginRequest;
import com.app.request.RegisterDto;
import com.app.response.DataResponse;
import com.app.response.RestResponse;
import com.app.service.AccessTokenService;
import com.app.service.UsersService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/api/v1/")
public class UserController {
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	AccessTokenService accessTokenService;
	
	public static final Logger logger = LoggerFactory.getLogger(TradeController.class);
	
	
	@PostMapping(value = "/sign-up/", produces = "application/json")
	public RestResponse signUp(@RequestBody RegisterDto registerDto) {
		
		try {
			Users users = usersService.signUp(registerDto);
			
			logger.info("User signup");
			return new DataResponse(201, "Success", users);
		}catch (AlreadyExistException e) {
			logger.error("Error:- {}",e);
			return new DataResponse(409, e.getMessage(), registerDto);
		}catch (Exception e) {
			logger.error("Error:- {}",e);
			return new DataResponse(500, e.getMessage(), registerDto);
		}
		
	}
	
	@PostMapping(value = "/sign-in/", produces = "application/json")
	public RestResponse signIn(@RequestBody LoginRequest loginReq) {
		try {
			
			
			
			
			Users users = usersService.signIn(loginReq.getEmail());

			if(users!= null) {

				// verify password
				if(!bCryptPasswordEncoder.matches(loginReq.getPassword(),users.getPassword())) {						
					return new DataResponse(401, StaticMessages.invalidCredential, null);
				}

				String token = Jwts.builder()
						//.setSubject(officer.getId()+"")
						.setSubject(users.getId()+"")
						.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
						.signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
						.compact();

				// Save token for logout process
				AccessToken aToken = new AccessToken();
				aToken.setUser(users);
				aToken.setToken(token);

				accessTokenService.insert(aToken);
				
				logger.info("User signin");
				return new DataResponse(200, StaticMessages.success, users, token);
			}else {
				return new DataResponse(404, StaticMessages.userNotFound, loginReq);
			}

		}catch (NotFoundException e) {
			logger.error("Error:- {}",e);
			return new DataResponse(404, e.getMessage(), loginReq);
		}catch (Exception e) {
			logger.error("Error:- {}",e);
			return new DataResponse(500, e.getMessage(), null);
		}


	}
	
	
}
