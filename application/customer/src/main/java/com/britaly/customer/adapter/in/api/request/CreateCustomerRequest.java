package com.britaly.customer.adapter.in.api.request;

import java.time.LocalDate;

import java.util.ArrayList;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateCustomerRequest {
    
    @NotNull
    private Person personCustomer;
    
    private Person affiliationFather;

    private Person affiliationMother;

    @NotNull
    private Integer nationality;
    
    private String email;

    @NotBlank
    private String phone;

    @NotNull
    private LocalDate dateBirth;

    private MaritalStatus maritalStatus;

    private String profession;

    @NotNull
    private Address customerAddress;

    @NotNull
    private ArrayList<Document> customerDocuments;
}
