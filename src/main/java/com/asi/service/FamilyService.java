package com.asi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.repository.FamilyRepository;

@Service
public class FamilyService {
	@Autowired
	FamilyRepository familyRepository;
}
