package com.britaly.customer.repository.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import com.britaly.customer.domain.GenderEnum;
import com.britaly.customer.domain.Person;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_PERSON")
public class PersonEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(columnDefinition = "gender")
    @Enumerated(EnumType.STRING)
    private GenderTypeEnum gender;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    public static PersonEntity fromDomain(Person domain) {
        return PersonEntity.builder()
            .firstName(domain.getFirstName())
            .lastName(domain.getLastName())
            .gender(GenderTypeEnum.valueOf(domain.getGender().toString()))
        .build();
    }

    public Person toDomain() {
        return Person.builder()
            .id(this.id)
            .firstName(this.firstName)
            .lastName(lastName)
            .gender(GenderEnum.valueOf(this.gender.toString()))
        .build();
    }
}
