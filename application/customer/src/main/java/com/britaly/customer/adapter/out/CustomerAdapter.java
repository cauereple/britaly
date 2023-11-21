package com.britaly.customer.adapter.out;

import org.springframework.stereotype.Component;

import com.britaly.customer.domain.Customer;
import com.britaly.customer.port.out.CustomerPort;
import com.britaly.customer.repository.CustomerRepository;
import com.britaly.customer.repository.entity.CustomerEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomerAdapter implements CustomerPort{

    private final CustomerRepository repository;

    @Override
    public Customer save(Customer customer) {
        return repository.save(CustomerEntity.fromDomain(customer)).toDomain();
    }
    
}
