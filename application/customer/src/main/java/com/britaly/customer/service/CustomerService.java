package com.britaly.customer.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Arrays;

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
import com.britaly.customer.service.exception.ServiceValidationException;
import com.britaly.customer.utils.Formatter;
import com.britaly.customer.utils.Validator;

import jakarta.transaction.Transactional;

import java.util.Objects;
import java.util.stream.Collectors;

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
    public ImmutablePair<Integer, String> create(CreateCustomerRequest request) throws ServiceValidationException {

        checkCountry(request.getNationality(), request.getCustomerAddress().getIdCountry());

        if(!Validator.isValidEmail(request.getEmail())) {
            throw new ServiceValidationException(Arrays.asList("Invalid E-mail"));
        }

        String phoneNumber = checkPhone(request.getPhone());

        Map<Integer, String> documentsFormatted = checkAndFormatterDocuments(request.getCustomerDocuments());

        checkCustomerDocuments(documentsFormatted);

        return createPersistence(request, phoneNumber, documentsFormatted);        
    }

    private void checkCountry(Integer nationality, Integer idCountryFromAddress) throws ServiceValidationException {

        Optional<Country> opCountry = countryPort.findById(nationality);

        Optional<Country> opAddressCountry = countryPort.findById(idCountryFromAddress);
        
        if(opCountry.isEmpty() || opAddressCountry.isEmpty()) {
            throw new ServiceValidationException(Arrays.asList("Country doesn't exist"));
        }
    }

    private String checkPhone(String phone) throws ServiceValidationException {
        
        String phoneNumber = Formatter.onlyNumbers(phone);

        if(!Validator.isValidPhone(phoneNumber)) {
            throw new ServiceValidationException(Arrays.asList("Invalid Phone Number"));
        }

        return phoneNumber;
    }
    private Map<Integer, String> checkAndFormatterDocuments(List<Document> documentsFromRequest) throws ServiceValidationException {
        List<Integer> documentsID = documentsFromRequest.stream().map(Document::getType).collect(Collectors.toList());
        List<DocumentType> documents = documentTypePort.findByIds(documentsID);
        return formatDocuments(documentsFromRequest, documents);
    }
    
    private Map<Integer, String> formatDocuments(List<Document> documentsFromRequest, List<DocumentType> documents) throws ServiceValidationException {
        Map<Integer, String> documentsFormatted = new HashMap<>();
    
        for (Document document : documentsFromRequest) {
            DocumentEnum documentEnum = null;
    
            for (DocumentType documentBanco : documents) {
                if (document.getType().equals(documentBanco.getId())) {
                    documentEnum = documentBanco.getDocumentName();
                    String numberFormatted = formatDocumentNumber(document.getNumber(), documentEnum);
                    documentsFormatted.put(documentBanco.getId(), numberFormatted);
                    break;
                }
            }
    
            if (documentEnum == null) {
                throw new ServiceValidationException(Arrays.asList("Document doesn't exist"));
            }
        }
    
        return documentsFormatted;
    }

    private String formatDocumentNumber(String number, DocumentEnum documentEnum) throws ServiceValidationException {
        switch (documentEnum) {
            case RG:
                String rgFormatted = Formatter.onlyNumbersWithX(number);
                if (!Validator.isRGValid(rgFormatted)) {
                    throw new ServiceValidationException(Arrays.asList("Invalid RG"));
                }
                return rgFormatted;
    
            case CPF:
                String cpfFormatted = Formatter.onlyNumbers(number);
                if (!Validator.isCPFValid(cpfFormatted)) {
                    throw new ServiceValidationException(Arrays.asList("Invalid CPF"));
                }
                return cpfFormatted;
    
            case CODICE_FISCALE:
                String codiceFiscaleFormatted = Formatter.onlyNumbersAndLetters(number);
                if(!Validator.isValidCodiceFiscale(codiceFiscaleFormatted)) {
                    throw new ServiceValidationException(Arrays.asList("Invalid Codice Fiscale"));
                }
                return codiceFiscaleFormatted;

            case CARTA_DI_IDENTITA:
                String cartaIdentitaFormatted = Formatter.onlyNumbersAndLetters(number);
                if (!Validator.isValidCartaIdentita(cartaIdentitaFormatted)) {
                    throw new ServiceValidationException(Arrays.asList("Invalid Carta di Identità"));
                }
                return cartaIdentitaFormatted;
    
            default:
                throw new ServiceValidationException(Arrays.asList("Invalid document type"));
        }
    }
    
    private void checkCustomerDocuments(Map<Integer, String> documentsFormatted) throws ServiceValidationException {

        List<DocumentCustomer> customerDocuments = documentCustomerPort.findByNumbers(new ArrayList<>(documentsFormatted.values()));

        if(!customerDocuments.isEmpty()) {

            List<String> existedDocuments = new ArrayList<>();

            for(DocumentCustomer document : customerDocuments) {
                existedDocuments.add("Document " + document.getNumber() + " already registrated");
            }

            throw new ServiceValidationException(existedDocuments);
        }
    }

    @Transactional
    private ImmutablePair<Integer, String> createPersistence(CreateCustomerRequest request, 
            String phoneNumber, Map<Integer, String> documentsFormatted) {

        Person personCustomer = savePerson(request.getPersonCustomer());
                    
        Integer personFatherId = savePerson(request.getAffiliationFather()).getId();

        Integer personMotherId = savePerson(request.getAffiliationMother()).getId();

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

        saveDocuments(documentsFormatted, customer.getId());

        saveAddress(request.getCustomerAddress(), customer.getId());

        return ImmutablePair.of(customer.getId(), customer.getUuid());
    }

    private Person savePerson(com.britaly.customer.adapter.in.api.request.Person person) {
        
        if(Objects.nonNull(person)) {
            return personPort.save(Person.builder()
                            .firstName(person.getFirstName())
                            .lastName(person.getLastName())
                            .gender(person.getGender())
                        .build());
        }

        return Person.builder().build();
    }

    private void saveDocuments(Map<Integer, String> documentsFormatted, Integer customerId) {

        List<DocumentCustomer> documentList = new ArrayList<>();
        
        for (Map.Entry<Integer, String> document : documentsFormatted.entrySet()) {
            documentList.add(DocumentCustomer.builder()
                .idCustomer(customerId)
                .idDocument(document.getKey())
                .number(document.getValue())
            .build());
        }

        documentCustomerPort.saveAll(documentList);
    }

    private void saveAddress(com.britaly.customer.adapter.in.api.request.Address request, Integer customerId) {

        Address address = addressPort.save(Address.builder()
                    .addressName(request.getAddressDescription())
                    .number(request.getNumber())
                    .complement(request.getComplement())
                    .city(request.getCity())
                    .state(request.getState())
                    .idCountry(request.getIdCountry())
                .build());

        addressCustomerPort.save(AddressCustomer.builder()
                    .idCustomer(customerId)
                    .idAddress(address.getId())
                .build());
    }
}