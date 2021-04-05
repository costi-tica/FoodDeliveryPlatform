package service;

import model.Address;
import model.users.Client;
import model.users.Courier;
import model.users.User;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public final class UserService {
    private final Scanner scanner;

    public UserService(){
        scanner = new Scanner(System.in);
    }

    private void setUserFields(User user){
        System.out.println("Name: ");
        user.setName(scanner.nextLine());

        System.out.println("Phone number: ");
        user.setPhoneNumber(scanner.nextLine());
    }

    public void setCourierFields(Courier courier){
        setUserFields(courier);
        System.out.println("Transport means: ('/' between) (ex: car, bike, on foot)");

        courier.setTransportMeans(Arrays.asList(scanner.nextLine().split("/")));
    }
    public void setClientFields(Client client){
        setUserFields(client);
    }

//  EDIT NAME (CLIENT AND COURIER)
    public void editName(User user, String name){
        user.setName(name);
    }
    public void editName(User user) {
        System.out.println("New name: ");
        user.setName(scanner.nextLine());
    }
//  EDIT PHONE NUMBER (CLIENT AND COURIER)
    public void editPhoneNumber(User user, String phoneNumber){
        user.setPhoneNumber(phoneNumber);
    }
    public void editPhoneNumber(User user){
        System.out.println("New phone number: ");
        user.setPhoneNumber(scanner.nextLine());
    }
}
