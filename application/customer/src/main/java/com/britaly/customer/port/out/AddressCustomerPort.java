package com.britaly.customer.port.out;

import com.britaly.customer.domain.AddressCustomer;

public interface AddressCustomerPort {
    
    public AddressCustomer save(AddressCustomer addressCustomer);
}
