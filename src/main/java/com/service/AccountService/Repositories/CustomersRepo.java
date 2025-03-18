package com.service.AccountService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.AccountService.models.Customer;

@Repository
public interface CustomersRepo extends JpaRepository<Customer, Long> {
	Customer findByCustomerId(Long customerId);
}