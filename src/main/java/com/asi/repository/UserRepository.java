package com.asi.repository;

import org.springframework.data.repository.CrudRepository;

import com.asi.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	public User findByEmailUser(String emailUser);
	
}
