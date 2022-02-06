package com.decathlon.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.decathlon.api.dto.UserDto;
import com.decathlon.api.entities.UserEntity;
import com.decathlon.api.repositories.UserRepository;
import com.decathlon.api.service.UserService;
import com.decathlon.api.shared.Utils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	Utils utils ;
	
	@Autowired
	UserRepository userRepository; 
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		if (userRepository.findByEmail(userDto.getEmail())!=null) {
			throw new RuntimeException("User Already Exists");
		}
		
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userDto, userEntity);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		userEntity.setUserId(utils.generateUserId(30));
		
		UserEntity savedUserEntity = userRepository.save(userEntity);
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(savedUserEntity, returnValue);
		
		return returnValue;
	}
	
	@Override
	public List<UserDto> getUserListService(int page, int limit) {
		List<UserDto> userDtoList = new ArrayList<UserDto> ();
		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<UserEntity> userEntityPage = userRepository.findAll(pageableRequest);
		for (UserEntity userEntity : userEntityPage) {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(userEntity, userDto);
			userDtoList.add(userDto);
		}
		return userDtoList;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}

	@Override
	public UserDto getUser(String email) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity==null) throw new UsernameNotFoundException(email);
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}
}
