package com.britaly.customer.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import com.britaly.customer.adapter.in.api.request.CreateCustomerRequest;
import com.britaly.customer.adapter.in.api.request.Document;
import com.britaly.customer.domain.DocumentType;
import com.britaly.customer.domain.Person;
import com.britaly.customer.domain.Address;
import com.britaly.customer.domain.AddressCustomer;
import com.britaly.customer.domain.Country;
import com.britaly.customer.domain.Customer;
import com.britaly.customer.domain.DocumentCustomer;
import com.britaly.customer.domain.DocumentEnum;
import com.britaly.customer.port.in.CustomerUC;
import com.britaly.customer.port.out.AddressCustomerPort;
import com.britaly.customer.port.out.AddressPort;
import com.britaly.customer.port.out.CountryPort;
import com.britaly.customer.port.out.CustomerPort;
import com.britaly.customer.port.out.DocumentCustomerPort;
import com.britaly.customer.port.out.DocumentTypePort;
import com.britaly.customer.port.out.PersonPort;
import com.britaly.customer.utils.Formatter;
import com.britaly.customer.utils.Validator;
import java.util.Objects;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService implements CustomerUC {

    private final CountryPort countryPort;
    private final DocumentTypePort documentTypePort;
    private final DocumentCustomerPort documentCustomerPort;
    private final PersonPort personPort;
    private final CustomerPort customerPort;
    private final AddressPort addressPort;
    private final AddressCustomerPort addressCustomerPort;

    @Override
    public ImmutablePair<Integer, String> create(CreateCustomerRequest request) {

        Optional<Country> opCountry = countryPort.findById(request.getNationality());
        
        if(opCountry.isEmpty()) {
            // Exception
        }

        if(!Validator.isValidEmail(request.getEmail())) {
            //Exception
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

        Map<Integer, String> documentsFormatted = new HashMap<>() {
            
        };

        for(Document document : request.getCustomerDocuments()) {
            
            DocumentEnum documentEnum = null;

            for(DocumentType documentBanco : documents) {
                if(document.getType().equals(documentBanco.getId())) {
                    documentEnum = documentBanco.getDocumentName();

                    switch(documentEnum) {
                        case RG -> {

                            String numberFormatted = Formatter.onlyNumbersWithX(document.getNumber());
                            documentsFormatted.put(documentBanco.getId(), numberFormatted);

                            if(!Validator.isRGValid(numberFormatted)) {
                                //Exception
                                return ImmutablePair.of(2, "ERRO RG");
                            }
                        }

                        case CPF -> {

                            String numberFormatted = Formatter.onlyNumbers(document.getNumber());
                            documentsFormatted.put(documentBanco.getId(), numberFormatted);


                            if(!Validator.isCPFValid(numberFormatted)) {
                                //Exception
                                return ImmutablePair.of(2, "ERRO CPF");
                            }
                        }

                        case CODICE_FISCALE -> {

                            String numberFormatted = Formatter.onlyNumbersAndLetters(document.getNumber());
                            documentsFormatted.put(documentBanco.getId(), numberFormatted);

                            if(!Validator.isValidCodiceFiscale(numberFormatted)) {
                                //Exception
                                return ImmutablePair.of(2, "ERRO CODICE FISCALE");
                            }
                        }

                        case CARTA_DI_IDENTITA -> {

                            String numberFormatted = Formatter.onlyNumbersAndLetters(document.getNumber());
                            documentsFormatted.put(documentBanco.getId(), numberFormatted);

                            if(!Validator.isValidCartaIdentita(numberFormatted)) {
                                //Exception
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

        List<String> lista1 = new ArrayList<>(documentsFormatted.values());

        List<DocumentCustomer> customerDocuments = documentCustomerPort.findByNumbers(new ArrayList<>(documentsFormatted.values()));

        if(!customerDocuments.isEmpty()) {
            //Exception
            return ImmutablePair.of(2, "ERRO Customer Documents");
        }

        Person personCustomer = personPort.save(Person.builder()
                            .firstName(request.getPersonCustomer().getFirstName())
                            .lastName(request.getPersonCustomer().getLastName())
                            .gender(request.getPersonCustomer().getGender())
                        .build());
                    
        Integer personFatherId = null;
        Integer personMotherId = null;
                        
        if(Objects.nonNull(request.getAffiliationFather())) {
            Person personFather = personPort.save(Person.builder()
                            .firstName(request.getAffiliationFather().getFirstName())
                            .lastName(request.getAffiliationFather().getLastName())
                            .gender(request.getAffiliationFather().getGender())
                            .build());
            
            personFatherId = personFather.getId();
        }
        
        if(Objects.nonNull(request.getAffiliationMother())) {
            Person personMother = personPort.save(Person.builder()
                            .firstName(request.getAffiliationMother().getFirstName())
                            .lastName(request.getAffiliationMother().getLastName())
                            .gender(request.getAffiliationMother().getGender())
                            .build());

            personMotherId = personMother.getId();
        }

        Customer customer = customerPort.save(Customer.builder()
                            .idPerson(personCustomer.getId())
                            .email(request.getEmail())
                            .phone(phoneNumber)
                            .dateBirth(request.getDateBirth())
                            .idAffiliationFather(personFatherId)
                            .idAffiliationMother(personMotherId)
                            .maritalStatus(request.getMaritalStatus())
                            .nationality(request.getNationality())
                            .profession(request.getProfession())
                            .build());

        List<DocumentCustomer> documentList = new ArrayList<>();
        
        for (Map.Entry<Integer, String> document : documentsFormatted.entrySet()) {
            documentList.add(DocumentCustomer.builder()
                .idCustomer(customer.getId())
                .idDocument(document.getKey())
                .number(document.getValue())
                .build());
        }

        List<DocumentCustomer> documentsFromEntity = documentCustomerPort.saveAll(documentList);

        Address address = addressPort.save(Address.builder()
                    .addressName(request.getCustomerAddress().getAddressDescription())
                    .number(request.getCustomerAddress().getNumber())
                    .complement(request.getCustomerAddress().getComplement())
                    .city(request.getCustomerAddress().getCity())
                    .state(request.getCustomerAddress().getState())
                    .idCountry(request.getCustomerAddress().getIdCountry())
                    .build());

        AddressCustomer addressCustomer = addressCustomerPort.save(AddressCustomer.builder()
                    .idCustomer(customer.getId())
                    .idAddress(address.getId())
                    .build());

        return ImmutablePair.of(customer.getId(), customer.getUuid());
    }

}