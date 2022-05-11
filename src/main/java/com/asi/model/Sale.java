package com.asi.model;

import java.sql.Date;

public class Sale {
	private int id_sale;
	private User user_sale;
	private CardInstance card_instance;
	private double price_sale;
	private Date date_sale;
	
	public Sale() {};
	
	public Sale(int id_sale, User user_sale, CardInstance card_instance, double price_sale, Date date_sale) {
		super();
		this.id_sale = id_sale;
		this.user_sale = user_sale;
		this.card_instance = card_instance;
		this.price_sale = price_sale;
		this.date_sale = date_sale;
	}
	public int getId_sale() {
		return id_sale;
	}
	public void setId_sale(int id_sale) {
		this.id_sale = id_sale;
	}
	public User getUser_sale() {
		return user_sale;
	}
	public void setUser_sale(User user_sale) {
		this.user_sale = user_sale;
	}
	public CardInstance getCard_instance() {
		return card_instance;
	}
	public void setCard_instance(CardInstance card_instance) {
		this.card_instance = card_instance;
	}
	public double getPrice_sale() {
		return price_sale;
	}
	public void setPrice_sale(double price_sale) {
		this.price_sale = price_sale;
	}
	public Date getDate_sale() {
		return date_sale;
	}
	public void setDate_sale(Date date_sale) {
		this.date_sale = date_sale;
	}
	
	
}
