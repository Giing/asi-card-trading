package com.asi.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.asi.model.Card;

public interface CardRepository extends CrudRepository<Card, Integer> {

	public List<Card> findByNameCard(String nameCard);
	
}

