package com.britaly.customer.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Address {
    
    private Integer id;
    private String addressName;
    private Integer number;
    private String complement;
    private String city;
    private String state;
    private Integer idCountry;
}
