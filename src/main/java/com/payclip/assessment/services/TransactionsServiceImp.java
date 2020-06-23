/**
 * 
 */
package com.payclip.assessment.services;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payclip.assessment.daos.TransactionsDAO;
import com.payclip.assessment.exception.TransactionNotFoundException;
import com.payclip.assessment.models.TransactionsSum;
import com.payclip.assessment.models.TransactionData;
import com.payclip.assessment.models.TransactionReport;
import com.payclip.assessment.models.Transaction;

/**
 * @author Enrique
 *
 */

@Service
public class TransactionsServiceImp implements TransactionService {

	@Autowired
	TransactionsDAO transDAO;

	@Override
	public Transaction addTransaction(TransactionData data) {
		String transId = UUID.randomUUID().toString();

		transDAO.insertTransaction(transId, data);

		return new Transaction(transId, data);
	}

	@Override
	public Transaction showTransaction(Long user_id, String transaction_id) {
		return transDAO.getAllTransactions().stream().filter(
				trans -> trans.getTransaction_id().equals(transaction_id) && trans.getData().getUser_id() == user_id)
				.findAny().get();
	}

	@Override
	public List<Transaction> getListOfTransactions(Long user_id) {
		
		List<Transaction> transactions = transDAO.getAllTransactions().stream().filter(trans -> trans.getData().getUser_id() == user_id).sorted(
				(t1, t2) -> LocalDate.parse(t1.getData().getDate()).compareTo(LocalDate.parse(t2.getData().getDate())))
				.collect(Collectors.toList());
		
		return transactions;
	}

	@Override
	public TransactionsSum sumTransactions(Long user_id) {
		List<Transaction> trans = getListOfTransactions(user_id);
		double sum = 0;

		for (Transaction tran : trans) {
			sum += tran.getData().getAmount();
		}

		return new TransactionsSum(user_id, sum);
	}

	@Override
	public List<TransactionReport> getTransactionsReport(Long user_id) {
		List<TransactionReport> reports = new LinkedList<TransactionReport>();
		List<Transaction> trans = getListOfTransactions(user_id);
		
		LocalDate startDate = null;
		LocalDate lastDate = null;
		int quantity = 0;
		double amount = 0;
		
		for (Transaction tran : trans) {
			LocalDate tranDate = LocalDate.parse(tran.getData().getDate());
			
			if(lastDate != null && lastDate.compareTo(tranDate) < 0) {
				reports.add(new TransactionReport(user_id, startDate.toString() + " " + startDate.getDayOfWeek(),
						lastDate.toString() + " " + lastDate.getDayOfWeek(), quantity, amount));
			}
			
			if(lastDate == null || lastDate.compareTo(tranDate) < 0) {
				startDate = getStartDate(tranDate);
				lastDate = getLastDate(tranDate);
				quantity = 1;
				amount = tran.getData().getAmount();
			}else {
				quantity++;
				amount += tran.getData().getAmount(); 
			}
		}
		
		if(startDate != null && lastDate != null)
			reports.add(new TransactionReport(user_id, startDate.toString() + " " + startDate.getDayOfWeek(),
				lastDate.toString() + " " + lastDate.getDayOfWeek(), quantity, amount));

		return reports;
	}

	private LocalDate getStartDate(LocalDate date) {

		int DOW = date.getDayOfWeek().getValue();
		LocalDate startDate = date;

		if (DOW > 5) {
			startDate = date.minusDays(DOW - 5);
		} else if (DOW < 5) {
			startDate = date.minusDays(DOW + 2);
		}

		return startDate.getMonth().equals(date.getMonth()) ? startDate : date.withDayOfMonth(1);
	}

	private LocalDate getLastDate(LocalDate date) {
		int DOW = date.getDayOfWeek().getValue();
		LocalDate lastDate = date;

		if (DOW > 4) {
			lastDate = date.plusDays((7 - DOW) + 4);
		} else if (DOW < 4) {
			lastDate = date.plusDays(4 - DOW);
		}

		return lastDate.getMonth().equals(date.getMonth()) ? lastDate
				: date.withDayOfMonth(date.getMonth().length(date.isLeapYear()));
	}

	@Override
	public Transaction randomTransaction() {
		List<Transaction> transactions = transDAO.getAllTransactions();
		
		if(transactions.isEmpty())
			throw new TransactionNotFoundException();
		
		Collections.shuffle(transactions);
		
		return transactions.get(0);
	}

}
