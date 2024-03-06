package com.britaly.customer.adapter.in.api;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.britaly.customer.adapter.in.api.request.CreateCustomerRequest;
import com.britaly.customer.adapter.in.api.response.CreateCustomerResponse;
import com.britaly.customer.adapter.in.api.response.DefaultResponse;
import com.britaly.customer.port.in.CustomerUC;
import com.britaly.customer.service.exception.ServiceValidationException;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/customer")
@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerUC customerUC;
    
    @Operation(summary = "Register customers")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DefaultResponse<CreateCustomerResponse>> createCustomer(@RequestBody @Valid final CreateCustomerRequest request) throws ServiceValidationException {

        ImmutablePair<Integer, String> response = customerUC.create(request);

        return ResponseEntity.ok(DefaultResponse.<CreateCustomerResponse>builder()
            .httpStatus(200)
            .resultData(CreateCustomerResponse.builder()
                .id(response.getLeft())
                .uuid(response.getRight())
            .build())
        .build());
    }
}