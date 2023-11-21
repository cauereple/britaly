package com.britaly.customer.port.out;

import com.britaly.customer.domain.Customer;

public interface CustomerPort {
    
    public Customer save(Customer customer);
}
