package model;

import java.util.Scanner;

public class Address {
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

    public void setFields(Scanner scanner){
        System.out.println("City: ");
        this.city = scanner.nextLine();
        System.out.println("Street: ");
        this.street = scanner.nextLine();
        System.out.println("Number: ");
        this.number = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Additional info: ");
        this.additionalInfo = scanner.nextLine();
    }
    
    public void updateFields(Scanner scanner, String[] fields){
        for (String field : fields){
            switch (field.toLowerCase()){
                case "city":
                    System.out.println("New city: ");
                    this.city = scanner.nextLine();
                    break;
                case "street":
                    System.out.println("New street: ");
                    this.street = scanner.nextLine();
                    break;
                case "number":
                    System.out.println("New number: ");
                    this.number = scanner.nextInt();
                    scanner.nextLine();
                    break;
                case "additional info":
                    System.out.println("New additional info: ");
                    this.additionalInfo = scanner.nextLine();
                    break;
            }
        }
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
