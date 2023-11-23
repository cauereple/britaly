package com.britaly.customer.repository.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.britaly.customer.domain.AddressCustomer;

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
@Table(name = "TB_ADDRESS_CUSTOMER")
public class AddressCustomerEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_customer")
    private Integer idCustomer;

    @Column(name = "id_address")
    private Integer idAddress;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    public static AddressCustomerEntity fromDomain(AddressCustomer domain) {
        return AddressCustomerEntity.builder()
            .id(domain.getId())
            .idCustomer(domain.getIdCustomer())
            .idAddress(domain.getIdAddress())
            .build();
    }

    public AddressCustomer toDomain() {
        return AddressCustomer.builder()
            .id(this.id)
            .idCustomer(this.idCustomer)
            .idAddress(this.idAddress)
            .build();
    }
}
