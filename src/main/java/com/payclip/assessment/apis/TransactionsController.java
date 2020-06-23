package com.payclip.assessment.apis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payclip.assessment.models.TransactionsSum;
import com.payclip.assessment.models.TransactionData;
import com.payclip.assessment.models.TransactionReport;
import com.payclip.assessment.exception.InvalidRequestException;
import com.payclip.assessment.models.Transaction;
import com.payclip.assessment.services.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

	@Autowired
	TransactionService transSrv;

	@PostMapping("/{user_id}/add")
	public Transaction addTransaction(@PathVariable Long user_id, @RequestBody TransactionData data) {
		
		if(data == null || user_id != data.getUser_id())
			throw new InvalidRequestException("Invalid user provided");
		
		if (data.getDate() == null || !data.getDate().matches("\\d{4}-\\d{2}-\\d{2}"))
			throw new InvalidRequestException("Invalid date provided, valid date has YYYY/mm/DD format");
        
		return transSrv.addTransaction(data);
	}

	@GetMapping("/{user_id}/singleTransaction/{transaction_id}")
	public Transaction showTransaction(@PathVariable Long user_id, @PathVariable String transaction_id) {
		return transSrv.showTransaction(user_id, transaction_id);
	}

	@GetMapping("/{user_id}")
	public List<Transaction> listTransactions(@PathVariable Long user_id) {
		return transSrv.getListOfTransactions(user_id);
	}

	@GetMapping("/{user_id}/sum")
	public TransactionsSum sumTransactions(@PathVariable Long user_id) {
		return transSrv.sumTransactions(user_id);
	}

	@GetMapping("/{user_id}/report")
	public List<TransactionReport> getTransactionsReport(@PathVariable Long user_id) {
		return transSrv.getTransactionsReport(user_id);
	}
	
	@GetMapping("/random")
	public Transaction getTransactionsReport() {
		return transSrv.randomTransaction();
	}

}
