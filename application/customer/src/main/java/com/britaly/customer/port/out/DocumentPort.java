package com.britaly.customer.port.out;

import java.util.List;

import com.britaly.customer.adapter.in.api.request.Document;

public interface DocumentPort {
    
    public List<Document> findByIds(List<Integer> ids);
}
