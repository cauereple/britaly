package com.britaly.customer.utils;

public class Formatter {
    
    public static String onlyNumbers(String phone) {
        return phone.replaceAll("[^0-9]", "");
    }
}
