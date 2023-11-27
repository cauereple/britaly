package com.britaly.customer.service.exception;

import java.util.List;

public class ServiceValidationException extends Exception {
    
    public ServiceValidationException(final List<String> listMsg) {
        super(listMsg.get(0));
    }
}
