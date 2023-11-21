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

        // List<DocumentCustomerEntity> listDocumentsCustomerEntity = new ArrayList<>();

        // DocumentCustomerEntity documentCustomerList = new DocumentCustomerEntity();
        // documentCustomerList.setId(1);
        // documentCustomerList.setIdCustomer(1);
        // documentCustomerList.setIdDocument(1);
        // documentCustomerList.setNumber("50628939X");
        // documentCustomerList.setCreatedDate(LocalDateTime.now());

        // listDocumentsCustomerEntity.add(documentCustomerList);

        if(listDocumentsCustomerEntity.isEmpty()) {
            return Collections.emptyList();
        }

        List<DocumentCustomer> list = new ArrayList<>();

        for(DocumentCustomerEntity entity : listDocumentsCustomerEntity) {
            list.add(entity.toDomain());
        }

        return list;
    }
    
}
