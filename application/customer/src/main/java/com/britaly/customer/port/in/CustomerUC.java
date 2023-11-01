package com.britaly.customer.port.in;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.britaly.customer.adapter.in.api.request.CreateCustomerRequest;

public interface CustomerUC {
    
    public ImmutablePair<Integer, String> create(CreateCustomerRequest request);
}
