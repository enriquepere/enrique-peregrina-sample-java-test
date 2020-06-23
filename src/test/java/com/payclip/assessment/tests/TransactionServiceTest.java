package com.payclip.assessment.tests;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.payclip.assessment.daos.TransactionsDAO;
import com.payclip.assessment.models.Transaction;
import com.payclip.assessment.models.TransactionData;
import com.payclip.assessment.models.TransactionReport;
import com.payclip.assessment.models.TransactionsSum;
import com.payclip.assessment.services.TransactionsServiceImp;
import com.payclip.assessment.utils.TestUtils;
import com.payclip.assessment.exception.TransactionNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

	@Mock
	TransactionsDAO transDAO;

	@InjectMocks
	TransactionsServiceImp tranSrv = new TransactionsServiceImp();

	private List<Transaction> mockTrans = null;
	private List<Transaction> mockReportTrans = null;
	private double mockSum = 0;

	@Before
	public void initialize() {
		mockTrans = TestUtils.createMockTransactions();
		mockSum = TestUtils.getSumOfMockTransactions(mockTrans);
		mockReportTrans = TestUtils.createReportMockTransactions();
	}

	@Test
	public void testAddTransaction() {
		Mockito.doNothing().when(transDAO).insertTransaction(Mockito.any(String.class),
				Mockito.any(TransactionData.class));
		TransactionData tranData = TestUtils.createMockTransaction().getData();

		Transaction tran = tranSrv.addTransaction(tranData);

		assertFalse(tran.getTransaction_id().isEmpty());
		assertEquals(tranData, tran.getData());
	}

	@Test
	public void testShowTransaction() {
		Mockito.when(transDAO.getAllTransactions()).thenReturn(mockTrans);
		Transaction tran = tranSrv.showTransaction(TestUtils.MOCK_USER, mockTrans.get(0).getTransaction_id());

		assertEquals(tran, mockTrans.get(0));
	}
			
	@Test
	public void testGetListOfTransactions() {
		Mockito.when(transDAO.getAllTransactions()).thenReturn(mockTrans);
		List<Transaction> trans = tranSrv.getListOfTransactions(TestUtils.MOCK_USER);

		assertEquals(mockTrans.size(), trans.size());
	}

	@Test
	public void testSumTransactions() {
		Mockito.when(transDAO.getAllTransactions()).thenReturn(mockTrans);
		TransactionsSum transSum = tranSrv.sumTransactions(TestUtils.MOCK_USER);

		assertEquals(new Double(transSum.getSum()), new Double(mockSum));
	}

	@Test
	public void testTransactionsReport() {
		Mockito.when(transDAO.getAllTransactions()).thenReturn(mockReportTrans);
		List<TransactionReport> report = tranSrv.getTransactionsReport(TestUtils.MOCK_USER);

		// "wk_start_date": "2020-03-01 SUNDAY",
		assertEquals(report.get(0).getWk_start_date(), "2020-03-01 SUNDAY");
		// "wk_finish_date": "2020-03-05 THURSDAY",
		assertEquals(report.get(0).getWk_finish_date(), "2020-03-05 THURSDAY");
		// "quantity": 1,
		assertEquals(new Long(report.get(0).getQuantity()), new Long(1));
		// "amount": 10
		assertEquals(new Double(report.get(0).getAmount()), new Double(10));

		// 2020-03-06 FRIDAY to 2020-03-12 THURSDAY ... NO TRANSACTIONS FROM THESE DATES

		// "wk_start_date": "2020-03-13 FRIDAY",
		assertEquals(report.get(1).getWk_start_date(), "2020-03-13 FRIDAY");
		// "wk_finish_date": "2020-03-19 THURSDAY",
		assertEquals(report.get(1).getWk_finish_date(), "2020-03-19 THURSDAY");
		// "quantity": 2,
		assertEquals(new Long(report.get(1).getQuantity()), new Long(2));
		// "amount": 20
		assertEquals(new Double(report.get(1).getAmount()), new Double(20));

		// "wk_start_date": "2020-03-20 FRIDAY",
		assertEquals(report.get(2).getWk_start_date(), "2020-03-20 FRIDAY");
		// "wk_finish_date": "2020-03-26 THURSDAY",
		assertEquals(report.get(2).getWk_finish_date(), "2020-03-26 THURSDAY");
		// "quantity": 1,
		assertEquals(new Long(report.get(2).getQuantity()), new Long(1));
		// "amount": 10
		assertEquals(new Double(report.get(2).getAmount()), new Double(10));

		// "wk_start_date": "2020-03-27 FRIDAY",
		assertEquals(report.get(3).getWk_start_date(), "2020-03-27 FRIDAY");
		// "wk_finish_date": "2020-03-31 TUESDAY",
		assertEquals(report.get(3).getWk_finish_date(), "2020-03-31 TUESDAY");
		// "quantity": 3,
		assertEquals(new Long(report.get(3).getQuantity()), new Long(3));
		// "amount": 30
		assertEquals(new Double(report.get(3).getAmount()), new Double(30));
	}
	
	@Test
	public void testRandomTransaction() {
		Mockito.when(transDAO.getAllTransactions()).thenReturn(mockTrans);
		Transaction tran1 = tranSrv.randomTransaction();
		Transaction tran2 = tranSrv.randomTransaction();
		
		assertNotSame(tran1, tran2);
	}

}