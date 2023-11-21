package com.britaly.customer.adapter.out;

import org.springframework.stereotype.Component;

import com.britaly.customer.domain.Person;
import com.britaly.customer.port.out.PersonPort;
import com.britaly.customer.repository.PersonRepository;
import com.britaly.customer.repository.entity.PersonEntity;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PersonAdapter implements PersonPort{

    private final PersonRepository repository;

    @Override
    public Person save(Person person) {
        return repository.save(PersonEntity.fromDomain(person)).toDomain();
    }
}
