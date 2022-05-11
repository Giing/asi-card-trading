package com.asi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.model.Card;
import com.asi.repository.CardRepository;

@Service
public class CardService {

	@Autowired
	CardRepository card_repository;
	
	public void addCard(Card card) {
        System.out.println(card);
        card_repository.save(card);
    }

	public Card getCard(int id) {
        Optional<Card> c = card_repository.findById(id);
        if (c.isPresent()) {
			return c.get();
		}else {
			return null;
		}

    }

}
