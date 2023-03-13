package com.spectrum.rewards.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "transactions")
public class Transaction {
	@Id
	private String id;
	@Field
	private String name;
	@Field
	private Double value;
	@Field
	private Date date;
	@Field
	private String username;
	
	public Transaction(String name, Double value, Date date, String username) {
		super();
		this.name = name;
		this.value = value;
		this.date = date;
		this.username = username;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}	
}

