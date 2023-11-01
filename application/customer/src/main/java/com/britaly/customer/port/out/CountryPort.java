package com.britaly.customer.port.out;


import java.util.Optional;

import org.springframework.stereotype.Component;

import com.britaly.customer.domain.Country;

@Component
public interface CountryPort {

    public Optional<Country> findCountryById(Integer id);
} 

