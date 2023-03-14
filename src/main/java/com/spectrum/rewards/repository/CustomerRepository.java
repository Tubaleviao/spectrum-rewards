package com.spectrum.rewards.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.spectrum.rewards.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, Integer> {
}