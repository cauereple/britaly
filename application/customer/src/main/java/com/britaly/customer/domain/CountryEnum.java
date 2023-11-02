package com.britaly.customer.domain;

public enum CountryEnum {
    
    BR("Brazil"), 
    IT("Italy");

    private CountryEnum(String country) {
        this.countryName = country;
    }

    private final String countryName;

    public String getCountryName() {
        return this.countryName;
    }
}