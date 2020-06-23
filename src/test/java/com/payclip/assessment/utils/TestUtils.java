package com.payclip.assessment.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import com.payclip.assessment.models.Transaction;
import com.payclip.assessment.models.TransactionData;

public class TestUtils {
	
	public final static long MOCK_USER = 345;

	public static double getSumOfMockTransactions(List<Transaction> trans) {
		double sum = 0;

		for (Transaction tran : trans) {
			sum += tran.getData().getAmount();
		}
		
		return sum;
	}
	
	public static List<Transaction> createMockTransactions(){
		List<Transaction> trans = new LinkedList<Transaction>();
		
		for(int i = 1; i <= 30; i++) {
			trans.add(createMockTransaction());
		}
		
		return trans;
	}
	
	public static Transaction createMockTransaction() {
		String transId = UUID.randomUUID().toString();
		double amount = (int) ((Math.random()*((5000-1)+1))+1);
		int day = (int) ((Math.random()*((20-1)+1))+1);
		
		return new Transaction( transId, new TransactionData(amount, "Joes Tacos", String.format("2019-12-%02d", day), TestUtils.MOCK_USER));
	}
	
	public static List<Transaction> createReportMockTransactions(){
		
		List<Transaction> trans = new LinkedList<Transaction>();
		String transId = UUID.randomUUID().toString();
		double amount = 10;
		String desc = "Joes Tacos";
		
		trans.add(new Transaction( transId, new TransactionData(amount, desc, String.format("2020-03-04"), TestUtils.MOCK_USER)));
		
		trans.add(new Transaction( transId, new TransactionData(amount, desc, String.format("2020-03-14"), TestUtils.MOCK_USER)));
		trans.add(new Transaction( transId, new TransactionData(amount, desc, String.format("2020-03-17"), TestUtils.MOCK_USER)));
		
		trans.add(new Transaction( transId, new TransactionData(amount, desc, String.format("2020-03-24"), TestUtils.MOCK_USER)));
		
		trans.add(new Transaction( transId, new TransactionData(amount, desc, String.format("2020-03-27"), TestUtils.MOCK_USER)));
		trans.add(new Transaction( transId, new TransactionData(amount, desc, String.format("2020-03-29"), TestUtils.MOCK_USER)));
		trans.add(new Transaction( transId, new TransactionData(amount, desc, String.format("2020-03-30"), TestUtils.MOCK_USER)));
		
		return trans;
	}
}
