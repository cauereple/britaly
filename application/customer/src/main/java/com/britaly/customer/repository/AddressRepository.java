package com.britaly.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.britaly.customer.repository.entity.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {
    
}
