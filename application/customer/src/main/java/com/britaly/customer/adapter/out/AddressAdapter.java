package com.britaly.customer.adapter.out;

import org.springframework.stereotype.Component;

import com.britaly.customer.domain.Address;
import com.britaly.customer.port.out.AddressPort;
import com.britaly.customer.repository.AddressRepository;
import com.britaly.customer.repository.entity.AddressEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AddressAdapter implements AddressPort {
    
    private final AddressRepository repository;

    @Override
    public Address save(Address address) {
        return repository.save(AddressEntity.fromDomain(address)).toDomain();
    }
    
}
