package com.britaly.customer.port.in;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.britaly.customer.adapter.in.api.request.CreateCustomerRequest;
import com.britaly.customer.service.exception.ServiceValidationException;

public interface CustomerUC {
    
    public ImmutablePair<Integer, String> create(CreateCustomerRequest request) throws ServiceValidationException;
}
