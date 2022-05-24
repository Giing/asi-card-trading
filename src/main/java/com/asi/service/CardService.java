package com.asi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.management.RuntimeErrorException;

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
	
	public List<Card> getAll() {
		List<Card> cards = (ArrayList<Card>) cardRepository.findAll();
		return cards;
	}
	
	public void deleteCard(int idCard) {
		cardRepository.deleteById(idCard);
	}
	
	public List<Card> getRandomCards(int nbToGenerate) {
		
		List<Card> cards = new ArrayList<Card>();
		Random r = new Random();
		int nbOfCardsInGame = (int) cardRepository.count();
		
		for (int i = 0; i < nbToGenerate; i++) {
			cards.add(this.getCard(r.nextInt(nbOfCardsInGame)));
		}
		
		if (cards.size() < nbToGenerate)
			throw new RuntimeException("Pas assez de cartes générées aléatoirement");
		
		return cards;
	}

}
