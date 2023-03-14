package com.spectrum.rewards;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.spectrum.rewards.model.Customer;
import com.spectrum.rewards.model.Transaction;

public class TransactionTest {
	
	private Transaction transaction;
	private Customer customer;
	
	@BeforeEach
	public void setUp() {
		customer = new Customer(1, "John Doe");
		transaction = new Transaction("Test Transaction", 100.0, new Date(), customer);
	}
	
	@Test
	public void testTransactionCreation() {
		assertNotNull(transaction, "Transaction should not be null");
	}
	
	@Test
	public void testTransactionFields() {
		assertEquals("Test Transaction", transaction.getName(), "Transaction name should match");
		assertEquals(100.0, transaction.getValue(), "Transaction value should match");
		assertEquals(customer, transaction.getCustomer(), "Transaction customer should match");
	}
	
	@Test
	public void testTransactionId() {
		Customer test_c = new Customer(3, "Emily Doe");
		transaction.setId("test_id");
		transaction.setName("test_name");
		Date now = new Date();
		transaction.setDate(now);
		transaction.setValue(4.2);
		transaction.setCustomer(test_c);
		assertEquals("test_id", transaction.getId(), "Transaction ID should match");
		assertEquals("test_name", transaction.getName(), "Transaction name should match");
		assertEquals(now, transaction.getDate(), "Transaction date should match");
		assertEquals(4.2, transaction.getValue(), "Transaction value should match");
		assertEquals(test_c, transaction.getCustomer(), "Transaction customer should match");
	}
}
