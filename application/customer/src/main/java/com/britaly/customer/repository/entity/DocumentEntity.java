package com.britaly.customer.repository.entity;

import java.time.LocalDateTime;

import com.britaly.customer.domain.Country;
import com.britaly.customer.domain.CountryEnum;
import com.britaly.customer.domain.CurrencyEnum;
import com.britaly.customer.domain.Document;
import com.britaly.customer.domain.DocumentEnum;

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
@Table(name = "TB_COUNTRY")
public class DocumentEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "type")
    private String type;

    @Column(name = "id_country")
    private Integer idCountry;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    public Document toDomain() {
        return Document.builder()
            .id(this.id)
            .documentName(DocumentEnum.valueOf(this.type))
        .build();
    }
}
