package com.asi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
}