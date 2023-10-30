package com.britaly.customer.adapter.in.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.britaly.customer.adapter.in.api.request.CreateCustomerRequest;
import com.britaly.customer.port.in.CustomerUC;

import lombok.RequiredArgsConstructor;

@RequestMapping("/customer")
@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerUC customerUC;
    
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCustomer(@RequestBody final CreateCustomerRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
