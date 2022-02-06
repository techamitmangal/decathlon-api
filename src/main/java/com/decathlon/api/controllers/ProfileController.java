package com.decathlon.api.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.decathlon.api.dto.UserDto;
import com.decathlon.api.model.requests.SignupReqModel;
import com.decathlon.api.model.responses.SignupResModel;
import com.decathlon.api.service.UserService;
import com.decathlon.api.shared.Utils;

@RestController
@RequestMapping("/v1/api/profile/")
public class ProfileController {

	@Autowired
	UserService userService ;
	
	@GetMapping(path = "signin")
	public String signinAction() {
		return "loggedin success";
	}

	@PostMapping(path = "signup")
	public SignupResModel signupAction(@RequestBody SignupReqModel signupReqModel) {
		SignupResModel signupResModel = new SignupResModel();
		
		if (signupReqModel.getEmail() == null || signupReqModel.getEmail().isBlank()) {
			signupResModel.setMessage("Please provide email id.");
			return signupResModel;
		}
		
		if (!Utils.isEmailValid(signupReqModel.getEmail())) {
			signupResModel.setMessage("Please provide valid email id.");
			return signupResModel;
		}
		
		if(signupReqModel.getPassword() == null || signupReqModel.getPassword().isBlank()) {
			signupResModel.setMessage("Please provide password");
			return signupResModel;
		}
		
		if(signupReqModel.getPassword().length()<8) {
			signupResModel.setMessage("password must be 8 characters long");
			return signupResModel;
		}
		
		if (signupReqModel.getFirstName() == null || signupReqModel.getFirstName().isBlank()) {
			signupReqModel.setFirstName("");
		}
		
		if (signupReqModel.getLastName() == null || signupReqModel.getLastName().isBlank()) {
			signupReqModel.setLastName("");
		}
		
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(signupReqModel, userDto);
		
		UserDto createUser = userService.createUser(userDto);
		BeanUtils.copyProperties(createUser, signupResModel);
		
		return signupResModel;
	}
	
	@GetMapping(path="getusers")
	public List<UserDto> GetUsers(@RequestParam(value="page", defaultValue="0") int page, 
			@RequestParam(value="limit", defaultValue="25") int limit) {

		List<UserDto> adminUserDtoList = userService.getUserListService(page, limit) ;
		
		return adminUserDtoList ;
	}
	
}