package com.spectrum.rewards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spectrum.rewards.model.Transaction;
import com.spectrum.rewards.service.TransactionService;


@RestController
@RequestMapping("/api")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@CrossOrigin
	@GetMapping("/transactions")
	public List<Transaction> getTransactions() {
		return transactionService.getTransactions();
	}
}
