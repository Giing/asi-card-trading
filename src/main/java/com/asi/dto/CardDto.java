package com.asi.dto;

import com.asi.model.Family;

public class CardDto {
	private int idCard;
	private String nameCard;
	private String descriptioncard;
	private String affinityCard;
	private int energyCard;
	private Family familyCard;
	
	public int getIdCard() {
		return idCard;
	}
	public void setIdCard(int idCard) {
		this.idCard = idCard;
	}
	public String getNameCard() {
		return nameCard;
	}
	public void setNameCard(String nameCard) {
		this.nameCard = nameCard;
	}
	public String getDescriptioncard() {
		return descriptioncard;
	}
	public void setDescriptioncard(String descriptioncard) {
		this.descriptioncard = descriptioncard;
	}
	public String getAffinityCard() {
		return affinityCard;
	}
	public void setAffinityCard(String affinityCard) {
		this.affinityCard = affinityCard;
	}
	public int getEnergyCard() {
		return energyCard;
	}
	public void setEnergyCard(int energyCard) {
		this.energyCard = energyCard;
	}
	public Family getFamilyCard() {
		return familyCard;
	}
	public void setFamilyCard(Family familyCard) {
		this.familyCard = familyCard;
	}
	
	
}
