package com.britaly.customer.service;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;
import com.britaly.customer.adapter.in.api.request.CreateCustomerRequest;
import com.britaly.customer.domain.Country;
import com.britaly.customer.port.in.CustomerUC;
import com.britaly.customer.port.out.CountryPort;
import java.util.Optional;

@Service
public class CustomerService implements CustomerUC {

    private CountryPort countryPort;

    @Override
    public ImmutablePair<Integer, String> create(CreateCustomerRequest request) {

        Optional<Country> opCountry =countryPort.findCountryById(request.getNationality());
        
        if(opCountry.isEmpty()) {
            // Exception
        }

       return ImmutablePair.of(1, "123456789");
    }
    
}