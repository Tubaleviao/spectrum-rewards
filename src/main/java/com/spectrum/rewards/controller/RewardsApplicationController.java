package com.spectrum.rewards.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RewardsApplicationController {

	
	@GetMapping("/")
	public String getUserRewards() {
		return "Application running";
	}

}
