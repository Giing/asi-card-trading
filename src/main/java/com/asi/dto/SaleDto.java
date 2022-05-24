package com.asi.dto;

import com.asi.model.Card;

public class SaleDto {
	
	private double priceSale;
	private int idSale;
	private Card card;
	private int idCard;
	
	public double getPriceSale() {
		return priceSale;
	}
	public void setPriceSale(double price) {
		this.priceSale = price;
	}
	public int getIdSale() {
		return idSale;
	}
	public void setIdSale(int idSale) {
		this.idSale = idSale;
	}
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	public int getIdCard() {
		return idCard;
	}
	public void setIdCard(int idCard) {
		this.idCard = idCard;
	}
	
}
