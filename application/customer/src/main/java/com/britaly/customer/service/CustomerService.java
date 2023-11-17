package com.britaly.customer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import com.britaly.customer.adapter.in.api.request.CreateCustomerRequest;
import com.britaly.customer.adapter.in.api.request.Document;
import com.britaly.customer.domain.DocumentType;
import com.britaly.customer.domain.Person;
import com.britaly.customer.domain.Country;
import com.britaly.customer.domain.DocumentCustomer;
import com.britaly.customer.domain.DocumentEnum;
import com.britaly.customer.port.in.CustomerUC;
import com.britaly.customer.port.out.CountryPort;
import com.britaly.customer.port.out.DocumentCustomerPort;
import com.britaly.customer.port.out.DocumentTypePort;
import com.britaly.customer.port.out.PersonPort;
import com.britaly.customer.utils.Formatter;
import com.britaly.customer.utils.Validator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService implements CustomerUC {

    private final CountryPort countryPort;
    private final DocumentTypePort documentTypePort;
    private final DocumentCustomerPort documentCustomerPort;
    private final PersonPort personPort;

    @Override
    public ImmutablePair<Integer, String> create(CreateCustomerRequest request) {

        Optional<Country> opCountry = countryPort.findById(request.getNationality());
        
        if(opCountry.isEmpty()) {
            // Exception
        }

        if(!Validator.isValidEmail(request.getEmail())) {
            return ImmutablePair.of(2, "ERRO EMAIL");
        }

        String phoneNumber = Formatter.onlyNumbers(request.getPhone());

        if(!Validator.isValidPhone(phoneNumber)) {
            // Exception
        }

        Optional<Country> opAddressCountry = countryPort.findById(request.getCustomerAddress().getIdCountry());
        
        if(opAddressCountry.isEmpty()) {
            // Exception
        }

        ArrayList<Integer> documentsID = new ArrayList<>();

        for(Document document : request.getCustomerDocuments()) {
            
            documentsID.add(document.getType());
        }

        List<DocumentType> documents = documentTypePort.findByIds(documentsID);

        List<String> documentsFormatted = new ArrayList<String>() {
            
        };

        for(Document document : request.getCustomerDocuments()) {
            
            DocumentEnum documentEnum = null;

            for(DocumentType documentBanco : documents) {
                if(document.getType().equals(documentBanco.getId())) {
                    documentEnum = documentBanco.getDocumentName();

                    switch(documentEnum) {
                        case RG -> {

                            String numberFormatted = Formatter.onlyNumbersWithX(document.getNumber());
                            documentsFormatted.add(numberFormatted);

                            if(!Validator.isRGValid(numberFormatted)) {
                                return ImmutablePair.of(2, "ERRO RG");
                            }
                        }

                        case CPF -> {

                            String numberFormatted = Formatter.onlyNumbers(document.getNumber());
                            documentsFormatted.add(numberFormatted);

                            if(!Validator.isCPFValid(numberFormatted)) {
                                return ImmutablePair.of(2, "ERRO CPF");
                            }
                        }

                        case CODICE_FISCALE -> {

                            String numberFormatted = Formatter.onlyNumbersAndLetters(document.getNumber());
                            documentsFormatted.add(numberFormatted);

                            if(!Validator.isValidCodiceFiscale(numberFormatted)) {
                                return ImmutablePair.of(2, "ERRO CODICE FISCALE");
                            }
                        }

                        case CARTA_DI_IDENTITA -> {

                            String numberFormatted = Formatter.onlyNumbersAndLetters(document.getNumber());
                            documentsFormatted.add(numberFormatted);

                            if(!Validator.isValidCartaIdentita(numberFormatted)) {
                                return ImmutablePair.of(2, "ERRO CARTA DI IDENTITA");
                            }
                        }
                    }
                }
            }

            if(documentEnum == null) {
                // Exception
            }
        }

        List<DocumentCustomer> customerDocuments = documentCustomerPort.findByNumbers(documentsFormatted);

        if(!customerDocuments.isEmpty()) {
            return ImmutablePair.of(2, "Customer Documents");
        }

        personPort.save(Person.builder()
                            .firstName(request.getPersonCustomer().getFirstName())
                            .lastName(request.getPersonCustomer().getLastName())
                            .gender(request.getPersonCustomer().getGender())
                        .build());

        return ImmutablePair.of(1, "123456789");
    }

}