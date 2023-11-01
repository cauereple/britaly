package com.britaly.customer.adapter.out;

import java.util.Optional;

import com.britaly.customer.domain.Country;
import com.britaly.customer.port.out.CountryPort;

public class CountryAdapter implements CountryPort{

    @Override
    public Optional<Country> findCountryById(Integer id) {
        throw new UnsupportedOperationException("Unimplemented method 'findCountryById'");
    }
    
}
