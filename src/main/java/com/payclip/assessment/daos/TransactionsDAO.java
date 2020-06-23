package com.payclip.assessment.daos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.payclip.assessment.models.TransactionData;
import com.payclip.assessment.models.Transaction;

@Component
public class TransactionsDAO {

	final private String FILE_PATH = "transactions.txt";
	final private String SERIALIZATION_FORMAT = "\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"";

	public TransactionsDAO() {
		try {
			// Creates file if does not exit when object is instantiated.
			Files.createFile(Paths.get(FILE_PATH));
		} catch (IOException e) {
			System.out.println("transactions.txt already exist");
		}
	}

	public void insertTransaction(String trans_id, TransactionData data) {

		String newLine = String.format(SERIALIZATION_FORMAT, trans_id, data.getAmount(), data.getDescription(),
				data.getDate(), data.getUser_id()) + System.lineSeparator();

		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_PATH), StandardOpenOption.APPEND)) {
			writer.write(newLine);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Transaction> getAllTransactions() {
		List<Transaction> trans = new LinkedList<Transaction>();

		try {
			Scanner myReader = new Scanner(new File(FILE_PATH));

			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				trans.add(covertLineToTransactionObj(data));
			}

			myReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return trans;
	}

	private Transaction covertLineToTransactionObj(String line) {
		String items[] = line.split(",");

		return new Transaction(removeQuotes(items[0]),
				new TransactionData(Double.parseDouble(removeQuotes(items[1])), removeQuotes(items[2]),
						removeQuotes(items[3]), Long.parseLong(removeQuotes(items[4]))));
	}

	private String removeQuotes(String line) {
		return line.substring(1, line.length() - 1);
	}

}
