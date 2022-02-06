package com.decathlon.api.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.decathlon.api.service.UserService;


@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
	private final UserService userDetailsService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Override
	protected void configure (HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers(SecurityConstants.SIGN_UP_URL, SecurityConstants.SIGN_IN_URL, 
				SecurityConstants.ADD_PRODUCT_URL, 
				SecurityConstants.GET_PRODUCT_LIST_URL,
				SecurityConstants.GET_PRODUCT_BY_ID_URL,
				SecurityConstants.GET_USERS_URL)
		.permitAll()
		.anyRequest().authenticated()
		.and()
		.addFilter(getAuthenticationFilter())
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	public AuthenticationFilter getAuthenticationFilter() throws Exception {
		final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
		filter.setFilterProcessesUrl("/admin/api/login");
		return filter;
	}
}
