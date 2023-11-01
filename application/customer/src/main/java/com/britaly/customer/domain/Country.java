package com.britaly.customer.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Generated
public class Country {
    
    private Integer id;
    private CountryEnum countryName;
    private CurrencyEnum currency;
}
