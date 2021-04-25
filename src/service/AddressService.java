package service;

import model.Address;
import model.Restaurant;
import model.users.Client;

import java.util.Scanner;

public final class AddressService {
    private final Scanner scanner;

    public AddressService(){
        scanner = new Scanner(System.in);
    }

    public Address createNewAddress(){
        String[] addressData;

        System.out.println("ccity/street/number/additional info");
        addressData = scanner.nextLine().split("/");

        return new Address.Builder()
                .withCity(addressData[0])
                .withStreet(addressData[1])
                .withNumber(Integer.parseInt(addressData[2]))
                .withAdditionalInfo(addressData[3])
                .build();
    }
    public void updateFields(Address address, String[] fields){
        for (String field : fields){
            switch (field.toLowerCase()) {
                case "city" -> {
                    System.out.println("New city: ");
                    address.setCity(scanner.nextLine());
                }
                case "street" -> {
                    System.out.println("New street: ");
                    address.setStreet(scanner.nextLine());
                }
                case "number" -> {
                    System.out.println("New number: ");
                    address.setNumber(scanner.nextInt());
                    scanner.nextLine();
                }
                case "additional info" -> {
                    System.out.println("New additional info: ");
                    address.setAdditionalInfo(scanner.nextLine());
                }
            }
        }
    }

//  ADD ADDRESS (CLIENT)
    public void addClientAddress(Client client, Address address){
        client.setAddress(address);
    }
    public void addClientAddress(Client client){
        client.setAddress(createNewAddress());
    }
//  ADD ADDRESS (RESTAURANT)
    public void addRestaurantAddress(Restaurant res, Address address){
        res.setAddress(address);
    }
    public void addRestaurantAddress(Restaurant res){
        res.setAddress(createNewAddress());
    }

//  EDIT ADDRESS (CLIENT)
    public void editClientAddress(Client client){
        Address address = client.getAddress();
        System.out.println("Adresa actuala: \n" + address.toString() + '\n');
        System.out.println("Ce campuri doriti sa modificati? (cu / intre)");

        updateFields(address, scanner.nextLine().split("/"));
    }
//  EDIT ADDRESS (RESTAURANT)
    public void editRestaurantAddress(Restaurant res){
        Address address = res.getAddress();
        System.out.println("Adresa actuala: \n" + address.toString() + '\n');
        System.out.println("Ce campuri doriti sa modificati? (cu / intre)");

        updateFields(address, scanner.nextLine().split("/"));
    }


}
