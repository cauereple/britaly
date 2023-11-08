package com.britaly.customer.port.out;

import java.util.List;

import com.britaly.customer.domain.DocumentType;
public interface DocumentPort {
    
    public List<DocumentType> findByIds(List<Integer> ids);
}
