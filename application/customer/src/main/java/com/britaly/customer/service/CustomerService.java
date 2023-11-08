package com.britaly.customer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import com.britaly.customer.adapter.in.api.request.CreateCustomerRequest;
import com.britaly.customer.adapter.in.api.request.Document;
import com.britaly.customer.domain.Country;
import com.britaly.customer.port.in.CustomerUC;
import com.britaly.customer.port.out.CountryPort;
import com.britaly.customer.port.out.DocumentPort;
import com.britaly.customer.utils.Formatter;
import com.britaly.customer.utils.Validator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService implements CustomerUC {

    private final CountryPort countryPort;
    private final DocumentPort documentPort;

    @Override
    public ImmutablePair<Integer, String> create(CreateCustomerRequest request) {

        Optional<Country> opCountry = countryPort.findById(request.getNationality());
        
        if(opCountry.isEmpty()) {
            // Exception
        }

        if(!Validator.isValidEmail(request.getEmail())) {
            // Exception
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

            // switch (document.getType()) {

            //     case 1 -> {
            //         String cpf = Formatter.onlyNumbers(document.getNumber());

            //         if(!Validator.isCPFValid(cpf)) {
            //             // Exception
            //         }

            //         documentsID.add(document.getType());
            //     }

            //     case 2 -> {
            //         String rg = Formatter.onlyNumbersWithX(document.getNumber());

            //         if(!Validator.isRGValid(rg)) {
            //             // Exception
            //         }

            //         documentsID.add(document.getType());
            //     }

            //     case 3 -> {
            //         DocumentEnum codiceFiscaleEnum = DocumentEnum.CODICE_FISCALE;

            //         if(!Validator.isValidCodiceFiscale(document.getNumber())) {
            //             // Exception
            //         }

            //         documentsID.add(document.getType());
            //     }

            //     case 4 -> {
            //         DocumentEnum cartaIdentitaEnum = DocumentEnum.CARTA_DI_IDENTITA;

            //         if(!Validator.isValidCartaIdentita(document.getNumber())) {
            //             // Exception
            //         }

            //         documentsID.add(document.getType());
            //     }

            //     default -> System.out.println("Document Invalid");
            // }
        }

        List<Document> documents = documentPort.findByIds(documentsID);

        return ImmutablePair.of(1, "123456789");
    }

}