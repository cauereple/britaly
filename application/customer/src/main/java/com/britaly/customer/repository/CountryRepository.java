package com.britaly.customer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.britaly.customer.repository.entity.CountryEntity;

public interface CountryRepository extends JpaRepository<CountryEntity, Integer>{

    Optional<CountryEntity> findById(Integer id);
}
