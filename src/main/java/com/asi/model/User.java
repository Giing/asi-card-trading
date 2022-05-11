package com.asi.model;

public class User {
	private int id_user;
	private String name_user;
	private String surname_user;
	private double money_user;
	private String password_user;
	
	public User() {
	}
	
	public User(int id_user, String name_user, String surname_user, double money_user, String password_user) {
		super();
		this.id_user = id_user;
		this.name_user = name_user;
		this.surname_user = surname_user;
		this.money_user = money_user;
		this.password_user = password_user;
	}
	
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public String getName_user() {
		return name_user;
	}
	public void setName_user(String name_user) {
		this.name_user = name_user;
	}
	public String getSurname_user() {
		return surname_user;
	}
	public void setSurname_user(String surname_user) {
		this.surname_user = surname_user;
	}
	public double getMoney_user() {
		return money_user;
	}
	public void setMoney_user(double money_user) {
		this.money_user = money_user;
	}
	public String getPassword_user() {
		return password_user;
	}
	public void setPassword_user(String password_user) {
		this.password_user = password_user;
	}
}
