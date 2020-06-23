package com.payclip.assessment.models;

public class Transaction {

	public Transaction(String transaction_id, TransactionData data) {
		this.transaction_id = transaction_id;
		this.data = data;
	}

	String transaction_id;
	TransactionData data;

	public TransactionData getData() {
		return data;
	}

	public void setData(TransactionData data) {
		this.data = data;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	
}
