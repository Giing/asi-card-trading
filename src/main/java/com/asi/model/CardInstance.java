package com.asi.model;

public class CardInstance {
	private int id_instance;
	private Card card_instance;
	private User user_instance;
	
	public CardInstance() {}
	
	public CardInstance(int id_instance, Card card_instance, User user_instance) {
		super();
		this.id_instance = id_instance;
		this.card_instance = card_instance;
		this.user_instance = user_instance;
	}
	
	public int getId_instance() {
		return id_instance;
	}
	public void setId_instance(int id_instance) {
		this.id_instance = id_instance;
	}
	public Card getCard_instance() {
		return card_instance;
	}
	public void setCard_instance(Card card_instance) {
		this.card_instance = card_instance;
	}
	public User getUser_instance() {
		return user_instance;
	}
	public void setUser_instance(User user_instance) {
		this.user_instance = user_instance;
	}
	
	
}
