package com.britaly.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.britaly.customer.repository.entity.DocumentEntity;

public interface DocumentRepository extends JpaRepository<DocumentEntity, Integer> {
    
    @Query(value = "select * from TB_DOCUMENT where id in (:ids)", nativeQuery = true)
    List<DocumentEntity> findByIds(@Param("ids") List<Integer> ids);
}
