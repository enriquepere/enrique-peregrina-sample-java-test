package com.payclip.assessment.models;

public class TransactionData {

	public TransactionData(double amount, String description, String date, long user_id) {
		this.amount = amount;
		this.description = description;
		this.date = date;
		this.user_id = user_id;
	}
	
	public TransactionData() {
		
	}
	
	double amount;
	String description;
	String date;
	long user_id;
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
}
