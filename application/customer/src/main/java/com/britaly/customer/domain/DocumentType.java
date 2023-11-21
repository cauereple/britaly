package com.britaly.customer.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class DocumentType {
    
    private Integer id;
    private DocumentEnum documentName;
}
