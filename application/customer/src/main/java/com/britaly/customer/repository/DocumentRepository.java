package com.britaly.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.britaly.customer.repository.entity.DocumentTypeEntity;

public interface DocumentRepository extends JpaRepository<DocumentTypeEntity, Integer> {
    
    @Query(value = "select * from TB_DOCUMENT_TYPE where id in (:ids)", nativeQuery = true)
    List<DocumentTypeEntity> findByIds(@Param("ids") List<Integer> ids);
}
