package com.asi.dto;

public class SaleTransactionDto {
	
	private double price;
	private int idSale;
	private int idCard;
	//TODO delete
	private int idUser;
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getIdSale() {
		return idSale;
	}
	public void setIdSale(int idSale) {
		this.idSale = idSale;
	}
	public int getIdCard() {
		return idCard;
	}
	public void setIdCard(int idCard) {
		this.idCard = idCard;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
		
}
