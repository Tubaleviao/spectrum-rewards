package com.spectrum.rewards.model;

import java.util.List;

public class RewardsDTO {
	private Customer customer;
	private List<Integer> months;
	
	public RewardsDTO(Customer customer, List<Integer> months) {
		super();
		this.customer = customer;
		this.months = months;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<Integer> getMonths() {
		return months;
	}
	public void setMonths(List<Integer> months) {
		this.months = months;
	}
	
}
