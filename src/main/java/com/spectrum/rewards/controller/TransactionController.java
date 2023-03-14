package com.spectrum.rewards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spectrum.rewards.model.Transaction;
import com.spectrum.rewards.service.TransactionService;


@RestController
@RequestMapping("/api")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("/transactions")
	@CrossOrigin
	public List<Transaction> getTransactions() {
		return transactionService.getTransactions();
	}
	@PostMapping("/transactions")
	@CrossOrigin
	public void addTransaction(@RequestBody Transaction transaction) {
		System.out.println(transaction.toString());
		transactionService.saveTransaction(transaction);
	}
	@DeleteMapping("/transactions")
	@CrossOrigin
	public void deleteTransaction(@RequestBody Transaction transaction) {
		transactionService.deleteTransaction(transaction);
	}
}
