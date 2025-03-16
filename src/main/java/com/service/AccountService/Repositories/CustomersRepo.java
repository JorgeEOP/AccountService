package com.service.AccountService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.service.AccountService.models.Account;
import com.service.AccountService.models.Customer;

import jakarta.transaction.Transactional;

@Repository
public interface CustomersRepo extends JpaRepository<Customer, Long> {
	Customer findByCustomerId(Long customerId);
}