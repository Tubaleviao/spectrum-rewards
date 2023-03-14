package com.spectrum.rewards;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.spectrum.rewards.model.Customer;

public class CustomerTest {
	
	private Customer customer;
	
	@BeforeEach
	public void setUp() {
		customer = new Customer(1, "John Doe");
	}
	
	@Test
	public void testGetId() {
		assertEquals(1, customer.getId());
	}
	
	@Test
	public void testSetId() {
		customer.setId(2);
		assertEquals(2, customer.getId());
	}
	
	@Test
	public void testGetName() {
		assertEquals("John Doe", customer.getName());
	}
	
	@Test
	public void testSetName() {
		customer.setName("Jane Doe");
		assertEquals("Jane Doe", customer.getName());
	}

}
