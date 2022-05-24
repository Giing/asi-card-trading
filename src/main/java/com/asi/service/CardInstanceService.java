package com.asi.service;

import java.util.ArrayList;  
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.model.CardInstance;
import com.asi.model.User;
import com.asi.repository.CardInstanceRepository;
import com.asi.repository.UserRepository;

@Service
public class CardInstanceService {
	
	@Autowired
	CardInstanceRepository cardInstancerepository;
	
	@Autowired
	UserRepository userRepository;
	
	public List<CardInstance> getCardsByUser(int idUser) {
		Optional<User> oUser = userRepository.findById(idUser);
		if (oUser.isPresent()) {
			Optional<List<CardInstance>> oCardInstance = cardInstancerepository.findByUserInstance(oUser.get());
			if (oCardInstance.isPresent())
				return oCardInstance.get();
		}
		return new ArrayList<CardInstance>();
	}
}
