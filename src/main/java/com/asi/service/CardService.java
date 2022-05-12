package com.asi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.model.Card;
import com.asi.repository.CardRepository;

@Service
public class CardService {

	@Autowired
	CardRepository cardRepository;
	
	public void addCard(Card card) {
        System.out.println(card);
        cardRepository.save(card);
    }

	public Card getCard(int id) {
        Optional<Card> c = cardRepository.findById(id);
        if (c.isPresent()) {
			return c.get();
		}else {
			return null;
		}

    }
	
	public void deleteCard(int idCard) {
		cardRepository.deleteById(idCard);
	}

}
