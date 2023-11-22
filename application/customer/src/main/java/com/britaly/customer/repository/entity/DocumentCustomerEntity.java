package com.britaly.customer.repository.entity;

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
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.britaly.customer.domain.DocumentCustomer;


@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_DOCUMENT_CUSTOMER")
public class DocumentCustomerEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_customer")
    private Integer idCustomer;

    @Column(name = "id_document_type")
    private Integer idDocument;

    @Column(name = "number")
    private String number;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    public static DocumentCustomerEntity fromDomain(DocumentCustomer domain) {
        return DocumentCustomerEntity.builder()
            .idCustomer(domain.getIdCustomer())
            .idDocument(domain.getIdDocument())
            .number(domain.getNumber())
            .build();
    }

    public DocumentCustomer toDomain() {
        return DocumentCustomer.builder()
            .id(this.id)
            .idCustomer(this.idCustomer)
            .idDocument(this.idDocument)
            .number(this.number)
        .build();
    }
}
