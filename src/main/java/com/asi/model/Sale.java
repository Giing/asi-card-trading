package com.asi.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Sale {
	@Id
	@GeneratedValue
	@Column
	private int idSale;
	@OneToOne
	private User userSale;
	@OneToOne
	private CardInstance cardInstance;
	@Column
	private double priceSale;
	@Column
	private Date dateSale;
	
	public Sale() {}

	public Sale(int idSale, User userSale, CardInstance cardInstance, double priceSale, Date dateSale) {
		super();
		this.idSale = idSale;
		this.userSale = userSale;
		this.cardInstance = cardInstance;
		this.priceSale = priceSale;
		this.dateSale = dateSale;
	}

	public int getIdSale() {
		return idSale;
	}

	public void setIdSale(int idSale) {
		this.idSale = idSale;
	}

	public User getUserSale() {
		return userSale;
	}

	public void setUserSale(User userSale) {
		this.userSale = userSale;
	}

	public CardInstance getCardInstance() {
		return cardInstance;
	}

	public void setCardInstance(CardInstance cardInstance) {
		this.cardInstance = cardInstance;
	}

	public double getPriceSale() {
		return priceSale;
	}

	public void setPriceSale(double priceSale) {
		this.priceSale = priceSale;
	}

	public Date getDateSale() {
		return dateSale;
	}

	public void setDateSale(Date dateSale) {
		this.dateSale = dateSale;
	};
	
	
	
}
