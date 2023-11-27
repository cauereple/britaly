package com.britaly.customer.adapter.in.api.response;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DefaultResponse<T> {
    
    private Integer httpStatus;
    private List<String> errors;
    private T resultData;

    @Builder
    public DefaultResponse(Integer httpStatus, List<String> errors, T resultData) {
        this.httpStatus = httpStatus;
        this.errors = errors;
        this.resultData = resultData;
    }
}
