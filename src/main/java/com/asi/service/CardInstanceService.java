package com.asi.service;

import java.util.ArrayList;  
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.model.Card;
import com.asi.model.CardInstance;
import com.asi.model.User;
import com.asi.repository.CardInstanceRepository;
import com.asi.repository.UserRepository;

@Service
public class CardInstanceService {
	
	@Autowired
	CardInstanceRepository cardInstancerepository;
	
	@Autowired
	CardService cardService;
	
	@Autowired
	UserRepository userRepository;
	
	private int numberOfCardsToGive = 5; 
	
	public List<CardInstance> getCardsByUser(int idUser) {
		Optional<User> oUser = userRepository.findById(idUser);
		if (oUser.isPresent()) {
			Optional<List<CardInstance>> oCardInstance = cardInstancerepository.findByUserInstance(oUser.get());
			if (oCardInstance.isPresent())
				return oCardInstance.get();
		}
		return new ArrayList<CardInstance>();
	}
	
	public void giveCardsToNewUser(User user) {
		
		List<Card> cardsToGiveToUser = cardService.getRandomCards(numberOfCardsToGive);
		
		Random r = new Random();
		for(int i = 0; i < this.numberOfCardsToGive; i++) {
			
			cardInstancerepository.save(new CardInstance(cardsToGiveToUser.get(i), user));
		}
	}
}
