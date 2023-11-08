package com.britaly.customer.utils;

public class Formatter {
    
    private Formatter(){}

    public static String onlyNumbers(String number) {
        return number.replaceAll("\\D", "");
    }

    public static String onlyNumbersWithX(String number) {
        return number.replaceAll("[^0-9Xx]", "").toUpperCase();
    }

    public static String onlyNumbersAndLetters(String number) {
        return number.replaceAll("[^0-9a-zA-Z]", "").toUpperCase();
    }
}
