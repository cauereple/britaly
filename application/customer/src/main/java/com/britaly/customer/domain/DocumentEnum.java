package com.britaly.customer.domain;

import java.util.stream.Stream;
import io.micrometer.common.util.StringUtils;

public enum DocumentEnum {
    RG("RG"),
    CPF("CPF"),
    CODICE_FISCALE("Codice Fiscale"),
    CARTA_DI_IDENTITA("Carta di Identita");

    private DocumentEnum(String document) {
        this.documentName = document;
    }

    private final String documentName;

    public String getDocumentName() {
        return this.documentName;
    }

    public static DocumentEnum fromTitle(String name){
        return Stream.of(DocumentEnum.values())
                    .filter(a -> StringUtils.isNotBlank(name) && a.getDocumentName().equals(name)).findFirst()
                    .orElse(null);
    }

    public static DocumentEnum fromDescription(String description) {

        for (DocumentEnum status: DocumentEnum.values()) {
            if (status.getDocumentName().equals(description)) {
                return status;
            }
        }

        throw new IllegalArgumentException("Description: " + description + " does not exists in ReprocessmentDocumentStatus");
    }
}
