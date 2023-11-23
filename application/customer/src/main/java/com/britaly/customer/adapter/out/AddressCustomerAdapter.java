package com.britaly.customer.adapter.out;

import org.springframework.stereotype.Component;

import com.britaly.customer.domain.AddressCustomer;
import com.britaly.customer.port.out.AddressCustomerPort;
import com.britaly.customer.repository.AddressCustomerRepository;
import com.britaly.customer.repository.entity.AddressCustomerEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AddressCustomerAdapter implements AddressCustomerPort {
    
    private final AddressCustomerRepository repository;

    @Override
    public AddressCustomer save(AddressCustomer addressCustomer) {
        return repository.save(AddressCustomerEntity.fromDomain(addressCustomer)).toDomain();
    }
}
