package com.asi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
