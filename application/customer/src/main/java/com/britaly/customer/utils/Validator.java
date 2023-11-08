package com.britaly.customer.utils;

import java.util.regex.Pattern;

import io.micrometer.common.util.StringUtils;

public class Validator {

    private static final String REGEX_EMAIL2 = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private static final String REGEX_EMAIL = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9]*[a-z0-9](?:[a-z0-9])?";

    private static final String REGEX_CPF = "\\d{11}";

    private static final String REGEX_RG = "\\d{8}[0-9X]";

    private static final String REGEX_CODICE_FISCALE = "/^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$/i";

    private static final String REGEX_CARTA_IDENTITA = "/\\b[[:alpha:]]{2}[[:digit:]]{5}[[:alpha:]]{2}\\b/gm";

    private Validator(){}
    
    public static boolean isValidEmail(String email) {

        if(StringUtils.isBlank(email)) {
            return true;
        }

        return Pattern.compile(REGEX_EMAIL)
            .matcher(email)
            .matches();
    }

    public static boolean isValidPhone(String phone) {
        return phone.length() > 10 || phone.length() < 14;
    }

    public static boolean isCPFValid(String number) {
        return Pattern.compile(REGEX_CPF)
            .matcher(number)
            .matches();
    }

    public static boolean isRGValid(String number) {
        return Pattern.compile(REGEX_RG)
            .matcher(number)
            .matches();
    }

    public static boolean isValidCodiceFiscale(String number) {

        return Pattern.compile(REGEX_CODICE_FISCALE)
            .matcher(number)
            .matches();
    }

    public static boolean isValidCartaIdentita(String number) {

        return Pattern.compile(REGEX_CARTA_IDENTITA)
            .matcher(number)
            .matches();
    }
}
