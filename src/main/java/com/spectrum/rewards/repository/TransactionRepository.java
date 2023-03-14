package com.spectrum.rewards.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spectrum.rewards.model.Customer;
import com.spectrum.rewards.model.Transaction;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
	public List<Transaction> findByCustomer(Customer customer);
}
