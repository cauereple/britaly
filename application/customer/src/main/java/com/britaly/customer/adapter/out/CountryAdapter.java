package com.britaly.customer.adapter.out;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.britaly.customer.domain.Country;
import com.britaly.customer.port.out.CountryPort;
import com.britaly.customer.repository.CountryRepository;
import com.britaly.customer.repository.entity.CountryEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CountryAdapter implements CountryPort {

    private final CountryRepository repository;

    @Override
    public Optional<Country> findById(Integer id) {

        Optional<CountryEntity> opCountryEntity = repository.findById(id);

        if(opCountryEntity.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(opCountryEntity.get().toDomain());
    }
    
}
