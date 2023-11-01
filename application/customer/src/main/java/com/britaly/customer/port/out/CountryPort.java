package com.britaly.customer.port.out;


import java.util.Optional;

import com.britaly.customer.domain.Country;

public interface CountryPort {

    public Optional<Country> findById(Integer id);
} 

