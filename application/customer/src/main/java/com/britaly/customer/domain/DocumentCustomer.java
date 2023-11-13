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
public class DocumentCustomer {
    
    private Integer id;
    private String number;
}
