package com.britaly.customer.domain;

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
}
