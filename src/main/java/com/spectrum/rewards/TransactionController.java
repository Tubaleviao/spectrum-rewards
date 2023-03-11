package com.spectrum.rewards;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class TransactionController {
	
	@Autowired
	private TransactionServiceImpl rewardsService;
	
	@GetMapping("/transactions")
	public List<Transaction> getTransactions() {
		return rewardsService.getTransactions();
	}
	@PostMapping("/transactions")
	public List<Transaction> addTransactions() {
		return rewardsService.getTransactions();
	}
}
