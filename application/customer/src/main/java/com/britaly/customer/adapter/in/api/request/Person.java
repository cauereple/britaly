package com.britaly.customer.adapter.in.api.request;

import com.britaly.customer.domain.GenderEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class Person {
    
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotNull
    private GenderEnum gender;
}
