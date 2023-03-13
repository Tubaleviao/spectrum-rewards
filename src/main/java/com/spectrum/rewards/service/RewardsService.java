package com.spectrum.rewards.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spectrum.rewards.model.RewardsDTO;
import com.spectrum.rewards.model.Transaction;
import com.spectrum.rewards.repository.TransactionRepository;

@Service
public class RewardsService {
	
	@Autowired
	private TransactionRepository transactionRepo;
	
	// Result: { username, rewards: { m1, m2, m3 } } 
	public RewardsDTO getRewards(String username){
		List<Transaction> userTransactions = transactionRepo.findByUsername(username);
		Date past3Months = getFirstDayOfXMonthsBehind(2);
		List<Transaction> filteredTransactions = userTransactions.stream()
				.filter(t -> t.getDate().after(past3Months) || t.getDate().equals(past3Months))
				.collect(Collectors.toList());
		return new RewardsDTO(username, calculatePoints(filteredTransactions));
	}
	
	public List<Integer> calculatePoints(List<Transaction> transactions){
		Date firstMonth = getFirstDayOfXMonthsBehind(2);
		Date secondMonth = getFirstDayOfXMonthsBehind(1);
		Date thirdMonth = getFirstDayOfXMonthsBehind(0);
		
		List<Transaction> firstMonthTransactions = transactions.stream().filter(
				t -> (t.getDate().after(firstMonth) && t.getDate().before(secondMonth) )
				|| t.getDate().equals(firstMonth))
				.collect(Collectors.toList());
		
		List<Transaction> secondMonthTransactions = transactions.stream().filter(
				t -> (t.getDate().after(secondMonth) && t.getDate().before(thirdMonth) )
				|| t.getDate().equals(secondMonth))
				.collect(Collectors.toList());
		
		List<Transaction> thirdMonthTransactions = transactions.stream().filter(
				t -> t.getDate().after(thirdMonth) || t.getDate().equals(thirdMonth))
				.collect(Collectors.toList());
		
		List<Integer> pointsList = new ArrayList<Integer>(); 
		pointsList.add(getMonthPoints(firstMonthTransactions.stream()));
		pointsList.add(getMonthPoints(secondMonthTransactions.stream()));
		pointsList.add(getMonthPoints(thirdMonthTransactions.stream()));
				
		return pointsList;
	}
	
	public Date getFirstDayOfXMonthsBehind(Integer monthsBehind) {
		Instant i = Instant.now();
		LocalDateTime pastmonths = LocalDateTime.ofInstant(i, ZoneId.systemDefault()).minusMonths(monthsBehind);
		int month =  pastmonths.atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue();
		int year = pastmonths.getYear();
		return Date.from(LocalDate.of(year,month, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
	
	public int getMonthPoints(Stream<Transaction> transactions) {
		return transactions.map(t -> {
			int transactionPoints = 0;
			if(t.getValue() <= 100) {
				if(t.getValue() > 50) {
					transactionPoints += Math.floor(t.getValue()-50);
				}
			}else {
				transactionPoints += 50 + Math.floor((t.getValue()-100) * 2);
			}
			return transactionPoints;
		}).reduce(0, Integer::sum);
	}
}
