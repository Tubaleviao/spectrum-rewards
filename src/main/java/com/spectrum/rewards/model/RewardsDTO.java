package com.spectrum.rewards.model;

import java.util.List;

public class RewardsDTO {
	private String username;
	private List<Integer> months;
	
	public RewardsDTO(String username, List<Integer> months) {
		super();
		this.username = username;
		this.months = months;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<Integer> getMonths() {
		return months;
	}
	public void setMonths(List<Integer> months) {
		this.months = months;
	}
	
}
