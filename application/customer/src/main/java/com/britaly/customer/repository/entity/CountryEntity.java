package com.britaly.customer.repository.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.britaly.customer.domain.Country;
import com.britaly.customer.domain.CountryEnum;
import com.britaly.customer.domain.CurrencyEnum;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.Objects;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_COUNTRY")
public class CountryEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "acronym")
    private String acronym;

    @Column(name = "country")
    private String country;

    @Column(name = "currency")
    private String currency;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    public static CountryEntity fromDomain(Country domain) {
        return CountryEntity.builder()
            .id(domain.getId())
            .acronym(Objects.nonNull(domain.getCountryName()) ? domain.getCountryName().toString() : null)
            .country(Objects.nonNull(domain.getCountryName()) ? domain.getCountryName().getCountryName() : null)
            .currency(Objects.nonNull(domain.getCurrency()) ? domain.getCurrency().toString() : null)
        .build();
    }
    
    public Country toDomain() {
        return Country.builder()
            .id(this.id)
            .countryName(CountryEnum.valueOf(this.acronym))
            .currency(CurrencyEnum.valueOf(this.currency))
        .build();
    }
}
