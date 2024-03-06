package com.britaly.customer.adapter.in.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;

import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpHeaders;

import com.britaly.customer.adapter.in.api.response.DefaultResponse;
import com.britaly.customer.service.exception.ServiceValidationException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(value = {ServiceValidationException.class})
    protected ResponseEntity<Object> handleServiceValidation(ServiceValidationException ex, WebRequest request) {

        DefaultResponse<Object> responseBody =  DefaultResponse.<Object>builder()
            .httpStatus(400)
            .errors(ex.getListMsg())
        .build();

        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {NullPointerException.class, InvalidDataAccessResourceUsageException.class})
    protected ResponseEntity<Object> handleInternalServerError(RuntimeException ex, WebRequest request) {

        DefaultResponse<Object> responseBody =  DefaultResponse.<Object>builder()
            .httpStatus(500)
            .errors(Arrays.asList(ex.getMessage()))
        .build();

        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
