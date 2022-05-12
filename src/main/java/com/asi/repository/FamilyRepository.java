package com.asi.repository;

import org.springframework.data.repository.CrudRepository;

import com.asi.model.Family;

public interface FamilyRepository extends CrudRepository<Family, Integer>{
	
	public Family findByNameFamily(String nameFamily);
}
