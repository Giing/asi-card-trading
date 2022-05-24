package com.asi.dto;

import com.asi.model.Card;

public class CardInstanceDto {
	private int idInstance;
	private Card cardInstance;
	private int iduser;
	
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
	public int getIduser() {
		return iduser;
	}
	public void setIduser(int iduser) {
		this.iduser = iduser;
	}
	
	
}
