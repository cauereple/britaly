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
import lombok.Setter;
import java.time.LocalDateTime;
import com.britaly.customer.domain.DocumentCustomer;


@Entity
@Builder
@Getter
@Setter
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

    @Column(name = "id_document")
    private Integer idDocument;

    @Column(name = "number")
    private String number;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    public DocumentCustomer toDomain() {
        return DocumentCustomer.builder()
            .id(this.id)
            .number(this.number)
        .build();
    }
}
