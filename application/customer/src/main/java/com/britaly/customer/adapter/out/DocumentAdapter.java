package com.britaly.customer.adapter.out;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.britaly.customer.domain.DocumentType;
import com.britaly.customer.port.out.DocumentPort;
import com.britaly.customer.repository.DocumentRepository;
import com.britaly.customer.repository.entity.DocumentEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DocumentAdapter implements DocumentPort {

    private final DocumentRepository repository;

    @Override
    public List<DocumentType> findByIds(List<Integer> ids) {

        List<DocumentEntity> listDocumentsEntity = repository.findByIds(ids);

        if(listDocumentsEntity.isEmpty()) {
            return Collections.emptyList();
        }

        List<DocumentType> list = new ArrayList<>();

        for(DocumentEntity entity : listDocumentsEntity) {
            list.add(entity.toDomain());
        }

        return list;

    }
    
}
