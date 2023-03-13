package com.spectrum.rewards.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spectrum.rewards.model.Transaction;
import com.spectrum.rewards.repository.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepo;
	
	@Autowired
	private RewardsService service;
	
	public List<Transaction> getTransactions() {
		// Inserting example data
		transactionRepo.deleteAll();
		if(transactionRepo.findAll().isEmpty()) {
			transactionRepo.save(new Transaction("cake", 51.1, new Date(), "Alvaro"));
			transactionRepo.save(new Transaction("juice", 101.0, service.getFirstDayOfXMonthsBehind(1), "Alvaro"));
			transactionRepo.save(new Transaction("sausage", 200.1, service.getFirstDayOfXMonthsBehind(3), "Alvaro"));
			
			transactionRepo.save(new Transaction("guitar", 500.0, new Date(), "Ashley"));
			transactionRepo.save(new Transaction("boots", 150.0, service.getFirstDayOfXMonthsBehind(1), "Ashley"));
			transactionRepo.save(new Transaction("apple", 200.1, service.getFirstDayOfXMonthsBehind(2), "Ashley"));
		}
		return transactionRepo.findAll();
	}

}
