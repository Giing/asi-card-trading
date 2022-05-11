package com.asi.model;

public class Family {
	private int id_family;
	private String name_family;
	
	public Family() {}
	
	public Family(int id_family, String name_family) {
		super();
		this.id_family = id_family;
		this.name_family = name_family;
	}
	public int getId_family() {
		return id_family;
	}
	public void setId_family(int id_family) {
		this.id_family = id_family;
	}
	public String getName_family() {
		return name_family;
	}
	public void setName_family(String name_family) {
		this.name_family = name_family;
	}
	
	
}
