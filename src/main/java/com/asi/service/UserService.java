package com.asi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.dto.LoginUserDto;
import com.asi.dto.RegisterUserDto;
import com.asi.model.User;
import com.asi.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public void addUser(RegisterUserDto user) {
		System.out.println("USER ADDED");
	}
	
	public boolean isInDatabase(RegisterUserDto userDto) {
		User user = userRepository.findByEmailUser(userDto.email);
		System.out.println(userDto);
		return user != null;
	}
	
	public boolean isValidUserRegistration(RegisterUserDto user) {
		boolean isValid = true;
		if(!this.isInDatabase(user)) {
			System.out.println("password : " + user.password);
			System.out.println("password comfrim: " + user.passwordConfirm);
			System.out.println("name : " + user.name);
			System.out.println("surname : " + user.surname);
			if (!user.password.equals(user.passwordConfirm)) {
				isValid = false;
			}
			
			if(user.name == null || user.name.isEmpty()) {
				isValid = false;
			}
			
			if(user.surname == null || user.surname.isEmpty()) {
				isValid = false;
			}
		} else {
			
		}
		return isValid;
	}
	
	public String login(LoginUserDto userDto) {
		User user = userRepository.findByEmailUser(userDto.email);
		
		if(user != null && user.getPasswordUser() == userDto.password) {
			return "";
		} else {
			return null;
		}
    }
}
