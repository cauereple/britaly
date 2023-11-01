package com.britaly.customer.adapter.in.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class Document {
    
    @NotNull
    private Integer type;

    @NotBlank
    private String number;
}
