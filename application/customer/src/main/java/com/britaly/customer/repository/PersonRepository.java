package com.britaly.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.britaly.customer.repository.entity.PersonEntity;

public interface PersonRepository extends JpaRepository<PersonEntity, Integer>{

}
