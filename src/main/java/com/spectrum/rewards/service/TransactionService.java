package com.spectrum.rewards.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spectrum.rewards.model.Customer;
import com.spectrum.rewards.model.Transaction;
import com.spectrum.rewards.repository.CustomerRepository;
import com.spectrum.rewards.repository.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepo;
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private RewardsService service;
	
	public List<Transaction> getTransactions() {
		
		// Inserting example data
		transactionRepo.deleteAll();
		if(transactionRepo.findAll().isEmpty()) {
			List<Date> months = new ArrayList<Date>();
			months.add(service.getFirstDayOfXMonthsBehind(3));
			months.add(service.getFirstDayOfXMonthsBehind(2));
			months.add(service.getFirstDayOfXMonthsBehind(1));
			Customer alvaro = new Customer(1, "Alvaro");
			Customer ashley = new Customer(2, "Ashley");
			customerRepo.save(alvaro);
			customerRepo.save(ashley);
			transactionRepo.save(new Transaction("cake", 51.1, new Date(), alvaro));
			transactionRepo.save(new Transaction("juice", 101.0, months.get(0) , alvaro));
			transactionRepo.save(new Transaction("sausage", 200.1, months.get(1), alvaro));
			
			transactionRepo.save(new Transaction("guitar", 500.0, new Date(), ashley));
			transactionRepo.save(new Transaction("boots", 150.0, months.get(2), ashley));
			transactionRepo.save(new Transaction("apple", 200.1, months.get(1), ashley));
		}
		return transactionRepo.findAll();
	}

}
