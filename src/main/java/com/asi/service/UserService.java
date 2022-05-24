package com.asi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.model.User;
import com.asi.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public void addUser(User user) {
		
	}
	
	public boolean isInDatabase(User user) {
		
		return true;
	}
	
	public boolean isValidUserRegistration(User user) {
		
		return true;
	}
}
