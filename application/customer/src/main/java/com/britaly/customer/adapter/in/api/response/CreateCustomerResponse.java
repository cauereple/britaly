package com.britaly.customer.adapter.in.api.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateCustomerResponse {
    
    private Integer id;
    private String uuid;
}
