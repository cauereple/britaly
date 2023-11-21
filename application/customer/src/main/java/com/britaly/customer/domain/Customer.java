package com.britaly.customer.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Customer {
    
    private Integer id;
    private String uuid;
    private Integer idPerson;
    private String email;
    private String phone;
    private LocalDate dateBirth;
    private Integer idAffiliationFather;
    private Integer idAffiliationMother;
    private MaritalStatusEnum maritalStatus;
    private Integer nationality;
    private String profession;
}
