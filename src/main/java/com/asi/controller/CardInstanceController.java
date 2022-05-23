package com.asi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.asi.model.CardInstance;
import com.asi.service.CardInstanceService;

@RestController
public class CardInstanceController {
	
	@Autowired
	CardInstanceService cardInstanceService;
	
	@RequestMapping(method=RequestMethod.GET, value="/cards/users/{idUser}")
	public List<CardInstance> getUserCards(@PathVariable int idUser) {
		
		System.out.println("Requête : Toutes les cartes de l'utilisateur");
		List<CardInstance> cards = cardInstanceService.getCardsByUser(idUser);
		
		return cards;
	}
}
