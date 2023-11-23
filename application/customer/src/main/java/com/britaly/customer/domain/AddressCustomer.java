package com.britaly.customer.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class AddressCustomer {
    
    private Integer id;
    private Integer idCustomer;
    private Integer idAddress;
}
