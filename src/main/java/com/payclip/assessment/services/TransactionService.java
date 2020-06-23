package com.payclip.assessment.services;

import java.util.List;

import com.payclip.assessment.models.TransactionsSum;
import com.payclip.assessment.models.TransactionData;
import com.payclip.assessment.models.TransactionReport;
import com.payclip.assessment.models.Transaction;

public interface TransactionService {

	public Transaction addTransaction(TransactionData data);

	public Transaction showTransaction(Long user_id, String transaction_id);

	public List<Transaction> getListOfTransactions(Long user_id);

	public TransactionsSum sumTransactions(Long user_id);

	public List<TransactionReport> getTransactionsReport(Long user_id);
	
	public Transaction randomTransaction();
}
