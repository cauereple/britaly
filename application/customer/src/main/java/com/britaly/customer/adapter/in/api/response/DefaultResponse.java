package com.britaly.customer.adapter.in.api.response;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DefaultResponse<T> {
    
    private Integer httpStatus;
    private List<String> errors;
    private T resultData;
}
