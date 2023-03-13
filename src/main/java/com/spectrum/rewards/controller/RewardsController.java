package com.spectrum.rewards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spectrum.rewards.model.RewardsDTO;
import com.spectrum.rewards.service.RewardsService;

@RestController
@RequestMapping("/api")
public class RewardsController {
	
	@Autowired
	private RewardsService rewardsService;
	
	@GetMapping("/users/{username}")
	public RewardsDTO getUserRewards(@PathVariable("username") String username) {
		return rewardsService.getRewards(username);
	}

}
