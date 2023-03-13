package com.spectrum.rewards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.spectrum.rewards.repository.TransactionRepository;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = TransactionRepository.class)
public class RewardsApplication {
	public static void main(String[] args) {
		SpringApplication.run(RewardsApplication.class, args);
	}
}

