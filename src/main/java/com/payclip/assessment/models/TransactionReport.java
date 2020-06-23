package com.payclip.assessment.models;

public class TransactionReport {

	public TransactionReport(long user_id, String wk_start_date, String wk_finish_date, long quantity, double amount) {
		this.user_id = user_id;
		this.wk_start_date = wk_start_date;
		this.wk_finish_date = wk_finish_date;
		this.quantity = quantity;
		this.amount = amount;
	}
	
	long user_id;
	String wk_start_date;
	String wk_finish_date;
	long quantity;
	double amount;
	
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	
	public String getWk_start_date() {
		return wk_start_date;
	}
	public void setWk_start_date(String wk_start_date) {
		this.wk_start_date = wk_start_date;
	}
	public String getWk_finish_date() {
		return wk_finish_date;
	}
	public void setWk_finish_date(String wk_finish_date) {
		this.wk_finish_date = wk_finish_date;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
}
