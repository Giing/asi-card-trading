package com.asi.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.asi.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	public Optional<User> findByEmailUser(String emailUser);
	
}
