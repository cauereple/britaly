package com.britaly.customer.adapter.in.api.request;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.validation.annotation.Validated;

import com.britaly.customer.domain.MaritalStatusEnum;

import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Validated
public class CreateCustomerRequest {
    
    @NotNull
    @Valid
    private Person personCustomer;
    
    @Valid
    private Person affiliationFather;

    @Valid
    private Person affiliationMother;

    @NotNull
    private Integer nationality;
    
    private String email;

    @NotBlank
    private String phone;

    @NotNull
    private LocalDate dateBirth;

    private MaritalStatusEnum maritalStatus;

    private String profession;

    @NotNull
    @Valid
    private Address customerAddress;

    @NotEmpty
    @Valid
    private ArrayList<Document> customerDocuments;


    @AssertFalse
    private boolean isDuplicatedDocuments() {
        
        Set<Integer> seenIds = new HashSet<>();

        for (Document document : this.customerDocuments) {
            if (!seenIds.add(document.getType())) {
                return true;
            }
        }   

        return false;
    }
}
