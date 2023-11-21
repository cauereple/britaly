package com.britaly.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.britaly.customer.repository.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
    
}
