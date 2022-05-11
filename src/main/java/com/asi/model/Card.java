package com.asi.model;

import javax.persistence.Entity;

@Entity
public class Card {
	private int id_card;
	private String name_card;
	private String description_card;
	private String affinity_card;
	private int energy_card;
	
	public Card() {}
	
	public Card(int id_card, String name_card, String description_card, String affinity_card, int energy_card) {
		super();
		this.id_card = id_card;
		this.name_card = name_card;
		this.description_card = description_card;
		this.affinity_card = affinity_card;
		this.energy_card = energy_card;
	}

	public int getId_card() {
		return id_card;
	}
	public void setId_card(int id_card) {
		this.id_card = id_card;
	}
	public String getName_card() {
		return name_card;
	}
	public void setName_card(String name_card) {
		this.name_card = name_card;
	}
	public String getDescription_card() {
		return description_card;
	}
	public void setDescription_card(String description_card) {
		this.description_card = description_card;
	}
	public String getAffinity_card() {
		return affinity_card;
	}
	public void setAffinity_card(String affinity_card) {
		this.affinity_card = affinity_card;
	}
	public int getEnergy_card() {
		return energy_card;
	}
	public void setEnergy_card(int energy_card) {
		this.energy_card = energy_card;
	}
}
