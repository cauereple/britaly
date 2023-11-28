package com.britaly.customer.service.exception;

import java.util.List;

import lombok.Getter;
public class ServiceValidationException extends Exception {
    
    @Getter
    private final List<String> listMsg;

    public ServiceValidationException(final List<String> listMsg) {
        super(listMsg.get(0));
        this.listMsg = listMsg;
    }
}
