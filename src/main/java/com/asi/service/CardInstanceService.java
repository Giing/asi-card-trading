package com.asi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.repository.CardInstanceRepository;

@Service
public class CardInstanceService {
	
	@Autowired
	CardInstanceRepository cardInstancerepository;
}
