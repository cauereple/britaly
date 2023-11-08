package com.britaly.customer.service;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;
import com.britaly.customer.adapter.in.api.request.CreateCustomerRequest;
import com.britaly.customer.domain.Country;
import com.britaly.customer.port.in.CustomerUC;
import com.britaly.customer.port.out.CountryPort;
import com.britaly.customer.utils.Formatter;
import com.britaly.customer.utils.Validator;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService implements CustomerUC {

    private final CountryPort countryPort;

    @Override
    public ImmutablePair<Integer, String> create(CreateCustomerRequest request) {

        Optional<Country> opCountry = countryPort.findById(request.getNationality());
        
        if(opCountry.isEmpty()) {
            // Exception
        }

        if(!Validator.isValidEmail(request.getEmail())) {
            // Exception
        }

        String phoneNumber = Formatter.onlyNumbers(request.getPhone());

        if(!Validator.isValidPhone(phoneNumber)) {
            // Exception
        }

        Optional<Country> opAddressCountry = countryPort.findById(request.getCustomerAddress().getIdCountry());
        
        if(opAddressCountry.isEmpty()) {
            // Exception
        }

        return ImmutablePair.of(1, "123456789");
    }

}