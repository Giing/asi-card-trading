package com.asi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asi.dto.CardDto;
import com.asi.dto.CardInstanceDto;
import com.asi.model.Card;
import com.asi.service.CardService;

@RestController
public class CardController {
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	CardService cardService;
	
	@RequestMapping("/index")
	public String index() {
		System.out.println("Ca passe !!!");
		return "Welcome";
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/card/")
	public void add(@RequestBody Card card) {
		cardService.addCard(card);
	}
	
	//method DELETE ?
	@RequestMapping(method=RequestMethod.DELETE,value="/card/{id}")
	public void delete(@PathVariable int idCard) {
		cardService.deleteCard(idCard);
	}
	
	@GetMapping("/api/cards/")
	public List<CardDto> getAll() {
		List<Card> cards = cardService.getAll();
		if (cards.isEmpty())
			throw new RuntimeException("Ressource not found");
		return cards.stream()
				.map(this::convertToDto)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/api/cards/{idCard}")
	public CardDto get(@PathVariable int idCard) {
		Card card = cardService.getCard(idCard);
		if (card == null)
			throw new RuntimeException("Ressource not found"); 
		return this.convertToDto(card);
	}
	
	private CardDto convertToDto(Card card) {
		CardDto cardDto = modelMapper.map(card, CardDto.class);
		return cardDto;
	}

}
