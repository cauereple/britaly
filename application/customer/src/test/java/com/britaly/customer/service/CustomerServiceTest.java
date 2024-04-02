package com.britaly.customer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import com.britaly.customer.adapter.in.api.request.Address;
import com.britaly.customer.adapter.in.api.request.CreateCustomerRequest;
import com.britaly.customer.adapter.in.api.request.Document;
import com.britaly.customer.domain.Country;
import com.britaly.customer.domain.DocumentCustomer;
import com.britaly.customer.domain.DocumentEnum;
import com.britaly.customer.domain.DocumentType;
import com.britaly.customer.port.out.AddressCustomerPort;
import com.britaly.customer.port.out.AddressPort;
import com.britaly.customer.port.out.CountryPort;
import com.britaly.customer.port.out.CustomerPort;
import com.britaly.customer.port.out.DocumentCustomerPort;
import com.britaly.customer.port.out.DocumentTypePort;
import com.britaly.customer.port.out.PersonPort;
import com.britaly.customer.service.exception.ServiceValidationException;

@TestInstance(Lifecycle.PER_METHOD)
class CustomerServiceTest {

    CountryPort countryPort = Mockito.mock(CountryPort.class);

    DocumentTypePort documentTypePort = Mockito.mock(DocumentTypePort.class);

    DocumentCustomerPort documentCustomerPort = Mockito.mock(DocumentCustomerPort.class);

    PersonPort personPort = Mockito.mock(PersonPort.class);

    CustomerPort customerPort = Mockito.mock(CustomerPort.class);

    AddressPort addressPort = Mockito.mock(AddressPort.class);

    AddressCustomerPort addressCustomerPort = Mockito.mock(AddressCustomerPort.class);

    CustomerService service = new CustomerService(countryPort, documentTypePort, documentCustomerPort, personPort, customerPort, addressPort, addressCustomerPort);

    @Test
    void createTestWhenCountryAndAddressCountryAreEmpty() throws ServiceValidationException {

        CreateCustomerRequest request = CreateCustomerRequest.builder()
            .customerAddress(Address.builder().build())
        .build();

        Exception e = assertThrows(ServiceValidationException.class,
            () -> service.create(request)
        );

        assertEquals("Country doesn't exist", e.getMessage());
    }

    @Test
    void createTestWhenCountryIsEmpty() throws ServiceValidationException {

        CreateCustomerRequest request = CreateCustomerRequest.builder()
            .nationality(1)
            .customerAddress(Address.builder().idCountry(2).build())
        .build();

        doReturn(Optional.of(Country.builder().build())).when(countryPort).findById(request.getCustomerAddress().getIdCountry());

        Exception e = assertThrows(ServiceValidationException.class,
            () -> service.create(request)
        );

        assertEquals("Country doesn't exist", e.getMessage());
    }

    @Test
    void createTestWhenAddressIsEmpty() throws ServiceValidationException {

        CreateCustomerRequest request = CreateCustomerRequest.builder()
            .nationality(1)
            .customerAddress(Address.builder().idCountry(2).build())
        .build();

        doReturn(Optional.of(Country.builder().build())).when(countryPort).findById(request.getNationality());

        Exception e = assertThrows(ServiceValidationException.class,
            () -> service.create(request)
        );

        assertEquals("Country doesn't exist", e.getMessage());
    }

    @Test
    void createTestWhenEmailIsInvalid() throws ServiceValidationException {

        CreateCustomerRequest request = CreateCustomerRequest.builder()
            .nationality(1)
            .customerAddress(Address.builder().idCountry(2).build())
            .email(null)
        .build();

        doReturn(Optional.of(Country.builder().build())).when(countryPort).findById(request.getCustomerAddress().getIdCountry());

        doReturn(Optional.of(Country.builder().build())).when(countryPort).findById(request.getNationality());

        Exception e = assertThrows(ServiceValidationException.class,
            () -> service.create(request)
        );

        assertEquals("Invalid E-mail", e.getMessage());
    }

    @Test
    void createTestWhenPhoneIsInvalid() throws ServiceValidationException {

        CreateCustomerRequest request = CreateCustomerRequest.builder()
            .nationality(1)
            .customerAddress(Address.builder().idCountry(2).build())
            .email("emailfortest@gmail.com")
            .phone("+3 8-427aXx")
        .build();

        doReturn(Optional.of(Country.builder().build())).when(countryPort).findById(request.getCustomerAddress().getIdCountry());

        doReturn(Optional.of(Country.builder().build())).when(countryPort).findById(request.getNationality());

        Exception e = assertThrows(ServiceValidationException.class,
            () -> service.create(request)
        );

        assertEquals("Invalid Phone Number", e.getMessage());
    }

    @ParameterizedTest
    @MethodSource("provideAllDocumentTypes")
    void createTestWhenDocumentsAreInvalid(int type, DocumentEnum documentEnum, String messageError) throws ServiceValidationException {

        ArrayList<Document> documents = new ArrayList<Document>();
        documents.add(Document.builder()
            .type(type)
            .number("06088aigp-x")
        .build());

        List<Integer> documentsID = new ArrayList<Integer>();
        documentsID.add(documents.get(0).getType());

        CreateCustomerRequest request = CreateCustomerRequest.builder()
            .nationality(1)
            .customerAddress(Address.builder().idCountry(2).build())
            .email("emailfortest@gmail.com")
            .phone("+39 379 168-4257aXx")
            .customerDocuments(documents)
        .build();

        doReturn(Optional.of(Country.builder().build())).when(countryPort).findById(request.getCustomerAddress().getIdCountry());

        doReturn(Optional.of(Country.builder().build())).when(countryPort).findById(request.getNationality());

        doReturn(List.of(DocumentType.builder()
                            .id(type)
                            .documentName(documentEnum)
                        .build()
                        )).when(documentTypePort).findByIds(documentsID);

        Exception e = assertThrows(ServiceValidationException.class,
            () -> service.create(request)
        );

        assertEquals(messageError, e.getMessage());
    }

    @Test
    void createTestWhenDocumentDoesntExist() throws ServiceValidationException {

        ArrayList<Document> documents = new ArrayList<Document>();
        documents.add(Document.builder()
            .type(1)
            .number("06080948aigp-x")
        .build());
        documents.add(Document.builder()
            .type(2)
            .number("rutcgg84t88z603a")
        .build());

        List<Integer> documentsID = new ArrayList<Integer>();
        documentsID.add(1);
        documentsID.add(documents.get(1).getType());

        CreateCustomerRequest request = CreateCustomerRequest.builder()
            .nationality(1)
            .customerAddress(Address.builder().idCountry(2).build())
            .email("emailfortest@gmail.com")
            .phone("+39 379 168-4257aXx")
            .customerDocuments(documents)
        .build();

        doReturn(Optional.of(Country.builder().build())).when(countryPort).findById(request.getCustomerAddress().getIdCountry());

        doReturn(Optional.of(Country.builder().build())).when(countryPort).findById(request.getNationality());

        doReturn(List.of(DocumentType.builder().build())).when(documentTypePort).findByIds(documentsID);

        Exception e = assertThrows(ServiceValidationException.class,
            () -> service.create(request)
        );

        assertEquals("Document doesn't exist", e.getMessage());
    }

    @Test
    void createTestWhenDocumentIsAlreadyRegistrated() throws ServiceValidationException {

        ArrayList<Document> documents = new ArrayList<Document>();
        documents.add(Document.builder()
            .type(1)
            .number("50.628.939-4")
        .build());

        List<Integer> documentsID = new ArrayList<Integer>();
        documentsID.add(documents.get(0).getType());

        List<DocumentCustomer> documentCustomer = new ArrayList<DocumentCustomer>();
        documentCustomer.add(DocumentCustomer.builder()
                        .id(1)
                        .idCustomer(1)
                        .idDocument(1)
                        .number("506289394")
                    .build());

        Map<Integer, String> documentsFormatted = new HashMap<>();
        documentsFormatted.put(1, "506289394");

        CreateCustomerRequest request = CreateCustomerRequest.builder()
            .nationality(1)
            .customerAddress(Address.builder().idCountry(2).build())
            .email("emailfortest@gmail.com")
            .phone("+39 379 168-4257aXx")
            .customerDocuments(documents)
        .build();

        doReturn(Optional.of(Country.builder().build())).when(countryPort).findById(request.getCustomerAddress().getIdCountry());

        doReturn(Optional.of(Country.builder().build())).when(countryPort).findById(request.getNationality());

        doReturn(List.of(DocumentType.builder()
                            .id(1)
                            .documentName(DocumentEnum.RG)
                            .build()
                        )).when(documentTypePort).findByIds(documentsID);

        doReturn(List.of(documentCustomer)).when(documentCustomerPort).findByNumbers(new ArrayList<>(documentsFormatted.values()));

        List<String> existedDocuments = new ArrayList<>();
        for(DocumentCustomer document : documentCustomer) {
            existedDocuments.add("Document " + document.getNumber() + " already registrated");
        }
        
        Exception e = assertThrows(ServiceValidationException.class,
            () -> service.create(request)
        );

        assertEquals(existedDocuments, e.getMessage());
    }

    private static Stream<Arguments> provideAllDocumentTypes() {
        return Stream.of(
            Arguments.of(1, DocumentEnum.RG, "Invalid RG"),
            Arguments.of(2, DocumentEnum.CPF, "Invalid CPF"),
            Arguments.of(3, DocumentEnum.CODICE_FISCALE, "Invalid Codice Fiscale"),
            Arguments.of(4, DocumentEnum.CARTA_DI_IDENTITA, "Invalid Carta di Identità")
        );
    }
}
