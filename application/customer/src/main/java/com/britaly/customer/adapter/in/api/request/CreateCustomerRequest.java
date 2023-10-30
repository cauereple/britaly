package com.britaly.customer.adapter.in.api.request;

import lombok.Getter;
import java.time.LocalDate;

@Getter
public class CreateCustomerRequest {
    
    private Person personCustomer;
    private Person affiliationFather;
    private Person affiliationMother;
    private Country nationality;
    private String email;
    private String phone;
    private LocalDate dateBirth;
    private MaritalStatus maritalStatus;
    private String profession;
}
