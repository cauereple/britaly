package com.britaly.customer.adapter.out;

import java.util.List;

import java.util.Collections;
import org.springframework.stereotype.Component;
import com.britaly.customer.domain.DocumentCustomer;
import com.britaly.customer.port.out.DocumentCustomerPort;
import com.britaly.customer.repository.DocumentCustomerRepository;
import com.britaly.customer.repository.entity.DocumentCustomerEntity;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;


@Component
@RequiredArgsConstructor
public class DocumentCustomerAdapter implements DocumentCustomerPort {

    private final DocumentCustomerRepository repository;

    @Override
    public List<DocumentCustomer> findByNumbers(List<String> numbers) {

        List<DocumentCustomerEntity> listDocumentsCustomerEntity = repository.findByNumbers(numbers);

        if(listDocumentsCustomerEntity.isEmpty()) {
            return Collections.emptyList();
        }

        List<DocumentCustomer> list = new ArrayList<>();

        for(DocumentCustomerEntity entity : listDocumentsCustomerEntity) {
            list.add(entity.toDomain());
        }

        return list;
    }

    @Override
    public List<DocumentCustomer> saveAll(List<DocumentCustomer> documents) {
    
        List<DocumentCustomerEntity> listDocumentsEntity = new ArrayList<>();

        for(DocumentCustomer document : documents) {
            
            DocumentCustomerEntity documentEntity = DocumentCustomerEntity.fromDomain(document);

            listDocumentsEntity.add(documentEntity);
        }

        List<DocumentCustomerEntity> listDocuments = repository.saveAll(listDocumentsEntity);

        List<DocumentCustomer> list = new ArrayList<>();

        for(DocumentCustomerEntity entity : listDocuments) {
            list.add(entity.toDomain());
        }
        
        return list;
    }
    
}
