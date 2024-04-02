package com.britaly.customer.adapter.in.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Address {

    @NotBlank
    private String addressDescription;

    @NotNull
    private int number;

    private String complement;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotNull
    private Integer idCountry;
}
