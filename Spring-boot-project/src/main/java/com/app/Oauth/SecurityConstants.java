package com.app.Oauth;
public class SecurityConstants {

	public static final String SECRET = "Secret#%JWT^%#&s";
	public static final long EXPIRATION_TIME = 864_000_000; // 10 DAYS
	public static final String TOKEN_PREFIX = "Bearer";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/api/auth/sign-up/";
}