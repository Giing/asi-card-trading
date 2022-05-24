package com.asi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asi.model.Card;
import com.asi.model.User;
import com.asi.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	
	@RequestMapping("/test")
	public String test() {
		System.out.println("Ca passe !!!");
		return "Bonjour le monde !";
	}
	
	@RequestMapping("api/user/register")
	public boolean register(@RequestBody User user) {
		
		if(!userService.isInDatabase(user)) {
			userService.addUser(user);
		}
		
		return true; 
	}
}
