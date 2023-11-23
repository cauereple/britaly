package com.britaly.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.britaly.customer.repository.entity.AddressCustomerEntity;

public interface AddressCustomerRepository extends JpaRepository<AddressCustomerEntity, Integer> {
    
}
