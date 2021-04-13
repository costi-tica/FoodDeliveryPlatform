package service;

import model.users.User;

import java.util.Scanner;

public final class UserService {
    private final Scanner scanner;

    public UserService(){
        scanner = new Scanner(System.in);
    }

    public String getCourierScannerData(){
        System.out.println("name/phone number/transport means (example: Ion/078563783/bike,car,on foot)");
        return scanner.nextLine();
    }
    public String getClientScannerData(){
        System.out.println("name/phone number");
        return scanner.nextLine();
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
