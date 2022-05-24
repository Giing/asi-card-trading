package com.asi.controller;

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
import com.asi.dto.RegisterUserDto;
import com.asi.service.UserService;
import org.modelmapper.ModelMapper;

@RestController
public class UserController {
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/test")
	public String test() {
		System.out.println("Ca passe !!!");
		return "Bonjour le monde !";
	}
	
	@RequestMapping(value = "/user/register", method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<?>  register(@RequestBody RegisterUserDto userDto) {
		//Create user 
		User user = convertToEntity(userDto);
		user.setMoneyUser(0);
		user.hashPassword();
		

		if (userService.isValidUserRegistration(user)) {
			// Send response
			userService.addUser(user);
			return new ResponseEntity<>("User registred", HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Bad request", HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/user/login", method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> login(@RequestBody LoginUserDto user) {

		String token = userService.login(user);

	    if(token != null) {
	        return new ResponseEntity<>(token, HttpStatus.OK);
	    }
	    
	    return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
	} 
	
	private User convertToEntity(RegisterUserDto registerUserDto) {
		User user = modelMapper.map(registerUserDto, User.class);
		return user;
	}
}
