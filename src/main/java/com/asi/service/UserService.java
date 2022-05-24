package com.asi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.dto.LoginUserDto;
import com.asi.model.Card;
import com.asi.model.User;
import com.asi.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public String login(LoginUserDto userDto) {
		User user = userRepository.findByEmailUser(userDto.email);
		
		if(user != null && user.getPasswordUser() == userDto.password) {
			return "";
		} else {
			return null;
		}
    }
}
