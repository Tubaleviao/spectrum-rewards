package com.spectrum.rewards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	@CrossOrigin
	@GetMapping("/users/{userId}")
	public RewardsDTO getUserRewards(@PathVariable("userId") Integer userId) {
		return rewardsService.getRewards(userId);
	}
	
	@CrossOrigin
	@GetMapping("/rewards")
	public List<RewardsDTO> getAllRewards() {
		return rewardsService.getAllRewards();
	}

}
