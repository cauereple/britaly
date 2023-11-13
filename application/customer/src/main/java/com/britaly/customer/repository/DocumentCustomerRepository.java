package com.britaly.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


import com.britaly.customer.repository.entity.DocumentCustomerEntity;

public interface DocumentCustomerRepository extends JpaRepository<DocumentCustomerEntity, Integer> {
    
    @Query(value = "select * from TB_DOCUMENT_CUSTOMER where number in (:numbers)", nativeQuery = true)
    List<DocumentCustomerEntity> findByNumbers(@Param("numbers") List<String> numbers);
}
