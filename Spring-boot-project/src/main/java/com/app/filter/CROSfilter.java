package com.app.filter;



import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.Oauth.SecurityConstants;
import com.app.dao.AuthTokenRepository;
import com.app.model.AccessToken;

import io.jsonwebtoken.Jwts;

@Component

public class CROSfilter  implements Filter{

	public static final String X_CLACKS_OVERHEAD = "X-Clacks-Overhead";

	@Autowired
	AuthTokenRepository authTokenRepository;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException{

		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization, type");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.addIntHeader("Access-Control-Max-Age", 180);			

		try {
			HttpServletRequest httpRequest = (HttpServletRequest) req;

			String requestArray  [] = new String [] {"sign-in", "sign-up"};		

			String[] url = httpRequest.getRequestURI().split("\\/");

			// swagger request handling
			String requestSwaggerArray  [] = new String [] {"swagger-ui.html", "configuration", "ui", "swagger-resources", "v2","api-docs", "configuration","security"};

			// if request not required token then send to rest controller
			if(httpRequest.getMethod().equalsIgnoreCase("OPTIONS") ){				
				chain.doFilter(req, res);
			}else if(( Arrays.asList(requestArray).indexOf(url[url.length -1]) != -1 || Arrays.asList(requestArray).indexOf(url[url.length -2]) != -1 ) ) {
				chain.doFilter(req, res);
			}

			// swagger request handling 			
			else if(( Arrays.asList(requestSwaggerArray).indexOf(url[url.length -1]) != -1 || Arrays.asList(requestSwaggerArray).indexOf(url[url.length -2]) != -1 ) && httpRequest.getMethod().equalsIgnoreCase("GET")) {
				chain.doFilter(req, res);
			}else if( Arrays.asList(url).indexOf("springfox-swagger-ui") != -1 || Arrays.asList(url).indexOf("images") != -1) {
				chain.doFilter(req, res);
			}

			// if request require token then verify token
			else{	
				this.varifyToken(httpRequest, response, chain);
			}
		}catch(Exception e) {			
			response.sendError(401, e.getMessage());
			return;							
		}		
	}



	private void varifyToken(HttpServletRequest httpRequest , HttpServletResponse res , FilterChain chain) throws IOException, ServletException {
		String idData = null;
		String id = null;
		
		String token = "";
		
		try {
			// get data from token
			idData = Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(httpRequest.getHeader("Authorization")).getBody().getSubject();									

			String arr[] = idData.split(":");

			// get user id
			id = arr[0];

			// get token from request
			token = httpRequest.getHeader("Authorization");

			// check token in data base
			//AccessToken AccessTokenData = authTokenRepository.findByToken(token);

			//if(AccessTokenData == null) {
			//	throw new IllegalStateException("Your auth token has been expired, please login again");
			//}

		}catch (Exception e) {
			throw new IllegalStateException("Your auth token has been expired, please login again");
		}

		if(id != null) {
			// set user id from future use
			httpRequest.setAttribute("id", id);	

			// set IP from future use
			httpRequest.setAttribute("ip", httpRequest.getRemoteAddr());
			httpRequest.getContentType();

			chain.doFilter(httpRequest, res);

		}else {
			throw new IllegalStateException("Please login first");				
		}
	}

	@Override
	public void destroy() {}

	@Override
	public void init(FilterConfig arg0) throws ServletException {	
		
	}




}