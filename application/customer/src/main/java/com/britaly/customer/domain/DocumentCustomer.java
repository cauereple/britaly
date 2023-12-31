package com.britaly.customer.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class DocumentCustomer {
    
    private Integer id;
    private Integer idCustomer;
    private Integer idDocument;
    private String number;
}
