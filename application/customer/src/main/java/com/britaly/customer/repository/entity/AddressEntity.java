package com.britaly.customer.repository.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.britaly.customer.domain.Address;

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

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_ADDRESS")
public class AddressEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "address")
    private String addressName;

    @Column(name = "number")
    private Integer number;

    @Column(name = "complement")
    private String complement;

    @Column(name = "city")
    private String city;
    
    @Column(name = "state")
    private String state;

    @Column(name = "id_country")
    private Integer idCountry;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    public static AddressEntity fromDomain(Address domain) {
        return AddressEntity.builder()
            .addressName(domain.getAddressName())
            .number(domain.getNumber())
            .complement(domain.getComplement())
            .city(domain.getCity())
            .state(domain.getState())
            .idCountry(domain.getIdCountry())
            .build();
    }

    public Address toDomain() {
        return Address.builder()
            .id(this.id)
            .addressName(this.addressName)
            .number(this.number)
            .complement(this.complement)
            .city(this.city)
            .state(this.state)
            .idCountry(this.idCountry)
            .build();
    }
}
