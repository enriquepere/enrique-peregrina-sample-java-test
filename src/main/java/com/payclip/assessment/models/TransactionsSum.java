package com.payclip.assessment.models;

public class TransactionsSum {

	public TransactionsSum(long user_id, double sum) {
		this.user_id = user_id;
		this.sum = sum;
	}
	
	long user_id;
	double sum;
	
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
}
