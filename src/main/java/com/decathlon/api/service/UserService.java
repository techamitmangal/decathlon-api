package com.decathlon.api.service;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.decathlon.api.dto.UserDto;

public interface UserService extends UserDetailsService{
	UserDto createUser(UserDto userDto);
	UserDto getUser(String email);
	List<UserDto> getUserListService(int page, int limit);
}
