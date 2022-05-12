package com.asi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asi.model.Card;
import com.asi.service.CardService;

@RestController
public class CardController {
	
	@Autowired
	CardService card_service;
	
	@RequestMapping("/index")
	public String index() {
		System.out.println("Ca passe !!!");
		return "Welcome";
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/card/")
	public void add(@RequestBody Card card) {
		System.out.println("Oui bonjour j'ai bien reçu la requête");
		card_service.addCard(card);
	}
	
	//method DELETE ?
	@RequestMapping(method=RequestMethod.DELETE,value="/card/{id}")
	public void delete(@PathVariable String card_id) {
		//call repo
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/card/{id}")
	public Card get(@PathVariable int card_id) {
		return card_service.getCard(card_id);		
	}

}
