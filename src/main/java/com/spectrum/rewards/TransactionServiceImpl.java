package com.spectrum.rewards;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepo;
	
	public List<Transaction> getTransactions() {
		if(transactionRepo.findAll().isEmpty()) {
			transactionRepo.save(new Transaction("cake", 20.1, new Date()));
		}
		return transactionRepo.findAll();
	}

}
