package com.asi.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asi.model.CardInstance;
import com.asi.model.Sale;
import com.asi.model.User;
import com.asi.repository.CardInstanceRepository;
import com.asi.repository.SaleRepository;
import com.asi.repository.UserRepository;


@Service
public class SaleService {
	
	@Autowired
	SaleRepository saleRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CardInstanceRepository cardInstanceRepository;
	
	public Sale getSale(int id) {
        Optional<Sale> s = saleRepository.findById(id);
        if (s.isPresent()) {
			return s.get();
		} else {
			return null;
		}
    }
	
	public List<Sale> getAllSales() {
		List<Sale> salesList = (List<Sale>) saleRepository.findAll();
		return salesList;
    }
	
	private void deduct(User user, double amount){
        user.setMoneyUser(user.getMoneyUser() - amount);
        userRepository.save(user);
    }

    private void deposit(User user, double amount){
    	user.setMoneyUser(user.getMoneyUser() + amount);
        userRepository.save(user);
    }	
	
    //TODO catch en dehors pour msg d'erreur ?
	@Transactional(rollbackFor = SQLException.class)
	private void buyTransaction(User buyer, Sale sale) {
		User seller = sale.getUserSale();
		double price = sale.getPriceSale();
		//money transfer
	    deduct(buyer, price);
	    deposit(seller, price);
	    //update card
	    CardInstance card = sale.getCardInstance();
	    card.setUserInstance(buyer);
	    cardInstanceRepository.save(card);
	    //delete sale
	    saleRepository.delete(sale);	    	
	}
	
	@Transactional(rollbackFor = SQLException.class)
	private void createOfferTransaction(User seller, CardInstance card, double price) {
		Sale sale = new Sale(seller, card, price);		
		saleRepository.save(sale);
		card.setUserInstance(null);
		cardInstanceRepository.save(card);
	}
	
	//TODO remove test sur User et use getCurrentUser() + utiliser des exceptions
	public int buy(int idSale, int idUser) {
		//check si user & sale existe
		Optional<User> buyer = userRepository.findById(idUser);
		Optional<Sale> sale = saleRepository.findById(idSale);
		
		if(!buyer.isPresent()) {
			return 500;
		}
		if(!sale.isPresent()) {
			return 500;
		}
		//check si argent user suffisant
		if(sale.get().getPriceSale() > buyer.get().getMoneyUser()) {
			return 500;
		}
		buyTransaction(buyer.get(), sale.get());
		return 200;
		
	}
	
	//TODO remove test sur User et use getCurrentUser() + utiliser des exceptions
	public int sell(int idUser, int idCardInstance, double price) {
		//checkout si user & sale existe
		Optional<User> seller = userRepository.findById(idUser);
		Optional<CardInstance> card = cardInstanceRepository.findById(idCardInstance);
		if(!seller.isPresent()) {
			return 500;
		}
		if(!card.isPresent()) {
			return 500;
		}
		//check user has the card
		if(1 == 0) {
			return 500;
		}
		createOfferTransaction(seller.get(), card.get(), price);
		return 200;
			
	}
}
