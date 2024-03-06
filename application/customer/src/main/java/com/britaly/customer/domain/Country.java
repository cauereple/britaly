package com.britaly.customer.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Country {
    
    private Integer id;
    private CountryEnum countryName;
    private CurrencyEnum currency;

    public static Country onlyId(Integer id) {
        return Country.builder()
            .id(id)
        .build();
    }
}
