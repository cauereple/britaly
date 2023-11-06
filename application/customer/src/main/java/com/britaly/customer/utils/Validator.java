package com.britaly.customer.utils;

import java.util.regex.Pattern;

import io.micrometer.common.util.StringUtils;

public class Validator {

    private static final String regexPatternEmail = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    
    public static boolean isValidEmail(String email) {

        if(StringUtils.isBlank(email)) {
            return true;
        }

        return Pattern.compile(regexPatternEmail)
            .matcher(email)
            .matches();
    }

    public static boolean isValidPhone(String phone) {

        if(phone.length() < 10 || phone.length() > 14){
            return false;
        }

        return true;
    }
}
