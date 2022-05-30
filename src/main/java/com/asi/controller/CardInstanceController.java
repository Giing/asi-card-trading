package com.asi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.asi.dto.CardInstanceDto;
import com.asi.model.CardInstance;
import com.asi.service.CardInstanceService;

@RestController
public class CardInstanceController {
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	CardInstanceService cardInstanceService;
	
	@GetMapping("/api/cards/users/{idUser}")
	public List<CardInstanceDto> getUserCards(@PathVariable int idUser) {
		
		List<CardInstance> cards = cardInstanceService.getCardsByUser(idUser);
		
		if (cards.isEmpty())
			throw new RuntimeException("Ressource not found");
		
		return cards.stream()
				.map(this::convertToDto)
				.collect(Collectors.toList());
	}
	
	private CardInstanceDto convertToDto(CardInstance cardInstance) {
		CardInstanceDto cardInstanceDto = modelMapper.map(cardInstance, CardInstanceDto.class);
		cardInstanceDto.setIduser(cardInstance.getUserInstance().getIdUser());
		return cardInstanceDto;
	}
	
	private CardInstance convertToEntity(CardInstanceDto cardInstanceDto) {
		return null;
	}
}
