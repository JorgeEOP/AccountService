package com.service.AccountService.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.AccountService.customers.Customer;

@Repository
public interface CustomersRepo extends JpaRepository<Customer, Long> {
}