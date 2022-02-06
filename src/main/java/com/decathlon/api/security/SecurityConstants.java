package com.decathlon.api.security;

import com.decathlon.api.SpringApplicationContext;

public class SecurityConstants {
	public static final long EXPIRATION_TIME = 864000000; // 10 days
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/v1/api/profile/signup";
	public static final String SIGN_IN_URL = "/v1/api/profile/signin";
	public static final String GET_USERS_URL = "/v1/api/profile/getusers";
	public static final String ADD_PRODUCT_URL = "/v1/api/addproduct";
	public static final String GET_PRODUCT_LIST_URL = "/v1/api/productlist";
	public static final String GET_PRODUCT_BY_ID_URL = "/v1/api/getproductbyid";
	
	public static String getTokenSecret() {
		AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("appProperties");
		return appProperties.getTokenSecret();
	}
}
