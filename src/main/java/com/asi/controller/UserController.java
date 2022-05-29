package com.asi.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.asi.model.User;
import com.asi.dto.LoginUserDto;
import com.asi.dto.ProfilUserDto;
import com.asi.dto.RegisterUserDto;
import com.asi.service.UserService;
import org.modelmapper.ModelMapper;

@RestController
public class UserController {
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/api/user/profile")
	@ResponseBody
	public ResponseEntity<?> getUserProfile() {
		User currentUser = userService.getRequestUser();
		if(currentUser != null) {			
			ProfilUserDto profilUserDto = modelMapper.map(currentUser, ProfilUserDto.class);
			return new ResponseEntity<>(profilUserDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No user connected", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/api/user/register", method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<?>  register(@RequestBody RegisterUserDto userDto) {
		try {
			User user = convertToEntity(userDto);
			userService.registerUser(user);
			return new ResponseEntity<>("User registred", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/api/user/login", method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> login(@RequestBody LoginUserDto userDto) {
		User user = userService.getUserByEmail(userDto.email);
		
		if(user != null) {			
			String token = userService.login(user, userDto.password);
			
			if(token != null) {
				ProfilUserDto profilUserDto = modelMapper.map(user, ProfilUserDto.class);
				profilUserDto.setToken(token);
				
				return new ResponseEntity<>(profilUserDto, HttpStatus.OK);
			}
		}
	    
	    return new ResponseEntity<String>("Bad request", HttpStatus.BAD_REQUEST);
	} 
	
	private User convertToEntity(RegisterUserDto registerUserDto) {
		User user = modelMapper.map(registerUserDto, User.class);
		return user;
	}
}
