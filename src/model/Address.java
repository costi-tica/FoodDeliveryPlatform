package model;

import java.util.Scanner;

public final class Address {
    private String city;
    private String street;
    private int number;
    private String additionalInfo;

    public Address(){}
    public Address(String city, String street, int number, String additionalInfo) {
        this.city = city;
        this.street = street;
        this.number = number;
        this.additionalInfo = additionalInfo;
    }

    @Override
    public String toString() {
        return "City: " + city + "\n" +
                "Street: " + street + "\n" +
                "Number: " + Integer.toString(number) + '\n' +
                "Additional info: " + additionalInfo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

}
