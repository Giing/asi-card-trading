package com.asi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.dto.LoginUserDto;
import com.asi.model.User;
import com.asi.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public void addUser(User user) {
		
		userRepository.save(user);
		System.out.println("User created : " + user.getEmailUser());
	}
	
	public boolean isInDatabase(User user) {
		User userFind = userRepository.findByEmailUser(user.getEmailUser());
		System.out.println(userFind);
		return userFind != null;
	}
	
	public boolean isValidUserRegistration(User user) {
		boolean isValid = true;
		if(!this.isInDatabase(user)) {
			System.out.println("password : " + user.getPasswordUser());
			System.out.println("email : " + user.getEmailUser());
			System.out.println("name : " + user.getNameUser());
			System.out.println("surname : " + user.getSurnameUser());
			
			if(user.getNameUser() == null || user.getNameUser().isEmpty()) {
				isValid = false;
			}
			
			if(user.getSurnameUser() == null || user.getSurnameUser().isEmpty()) {
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
