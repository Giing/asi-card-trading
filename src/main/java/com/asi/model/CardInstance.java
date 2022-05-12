package com.asi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class CardInstance {
	@Id
	@GeneratedValue
	@Column
	private int idInstance;
	@OneToOne
	private Card cardInstance;
	@OneToOne
	private User userInstance;
	
	public CardInstance() {}

	public CardInstance(int idInstance, Card cardInstance, User userInstance) {
		super();
		this.idInstance = idInstance;
		this.cardInstance = cardInstance;
		this.userInstance = userInstance;
	}

	public int getIdInstance() {
		return idInstance;
	}

	public void setIdInstance(int idInstance) {
		this.idInstance = idInstance;
	}

	public Card getCardInstance() {
		return cardInstance;
	}

	public void setCardInstance(Card cardInstance) {
		this.cardInstance = cardInstance;
	}

	public User getUserInstance() {
		return userInstance;
	}

	public void setUserInstance(User userInstance) {
		this.userInstance = userInstance;
	}
	
	
	
	
}
