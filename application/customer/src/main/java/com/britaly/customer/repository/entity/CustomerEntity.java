package com.britaly.customer.repository.entity;

import java.time.LocalDateTime;
import java.time.LocalDate;
import org.hibernate.annotations.CreationTimestamp;
import com.britaly.customer.domain.Customer;
import com.britaly.customer.domain.MaritalStatusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_CUSTOMER")
public class CustomerEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "id_person")
    private Integer idPerson;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "date_birth")
    private LocalDate dateBirth;

    @Column(name = "affiliation_father")
    private Integer idAffiliationFather;

    @Column(name = "affiliation_mother")
    private Integer idAffiliationMother;

    @Column(name = "marital_status")
    private String maritalStatus;

    @Column(name = "nationality")
    private Integer idCountry;

    @Column(name = "profession")
    private String profession;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    public static CustomerEntity fromDomain (Customer domain) {
        return CustomerEntity.builder()
            .uuid(UUID.randomUUID().toString())
            .idPerson(domain.getIdPerson())
            .email(domain.getEmail())
            .phone(domain.getPhone())
            .dateBirth(domain.getDateBirth())
            .idAffiliationFather(domain.getIdAffiliationFather())
            .idAffiliationMother(domain.getIdAffiliationMother())
            .maritalStatus(domain.getMaritalStatus().toString())
            .idCountry(domain.getNationality())
            .profession(domain.getProfession())
            .build();
    }

    public Customer toDomain() {
        return Customer.builder()
            .id(this.id)
            .uuid(this.uuid)
            .idPerson(this.idPerson)
            .email(this.email)
            .phone(this.phone)
            .dateBirth(this.dateBirth)
            .idAffiliationFather(this.idAffiliationFather)
            .idAffiliationMother(this.idAffiliationMother)
            .maritalStatus(MaritalStatusEnum.valueOf(this.maritalStatus))
            .nationality(this.idCountry)
            .profession(this.profession)
            .build();
    }
}
