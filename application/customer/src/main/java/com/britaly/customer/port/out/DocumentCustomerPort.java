package com.britaly.customer.port.out;

import java.util.List;
import com.britaly.customer.domain.DocumentCustomer;

public interface DocumentCustomerPort {
    
    public List<DocumentCustomer> findByNumbers(List<String> numbers);
}
