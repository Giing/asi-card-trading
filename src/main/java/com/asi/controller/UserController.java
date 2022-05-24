package com.asi.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

@RestController
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@RequestMapping("/test")
	public String test() {
		User currentUser = userService.getRequestUser();
		return "Bonjour utilisateur identifié: " + currentUser.getEmailUser();
	}
	
	@RequestMapping(value = "/user/register", method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<?>  register(@RequestBody RegisterUserDto user) {
		System.out.println("Youhou");
		boolean userIsRegistred= false;
		userIsRegistred = userService.isInDatabase(user);
		if(!userIsRegistred) {
			userIsRegistred = userService.isValidUserRegistration(user);
			if (userIsRegistred) {
				userService.addUser(user);
			}
		}
		
		if (userIsRegistred) {
			return new ResponseEntity<>("User registred", HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Bad request", HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/user/login", method=RequestMethod.POST, produces = "application/json")
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
}
