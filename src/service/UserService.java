package service;

import model.users.User;

import java.util.*;

public final class UserService {
    private final Scanner scanner;

    public UserService(){
        scanner = new Scanner(System.in);
    }

//  ACCOUNT INFO
    private boolean passwordsDoNotMatch(Map<String, String> data){
        if (data.get("password").equals(data.get("repassword")))
            return false;
        int option;
        System.out.println("Passwords do not match!");
        System.out.println("""
              1) Try again
              2) Exit
              OPTION""");
        option = scanner.nextInt();
        scanner.nextLine();

        if (option == 2) System.exit(0);

        return true;
    }
    public Map<String, String> getUserScannerData(){
        Map<String, String> userData = new HashMap<>();
        System.out.println("Name:");
        userData.put("name", scanner.nextLine());
        System.out.println("Phone number:");
        userData.put("phone number", scanner.nextLine());
        System.out.println("Email:");
        userData.put("email", scanner.nextLine());
        System.out.println("Password:");
        userData.put("password", scanner.nextLine());
        System.out.println("Reintroduce the password:");
        userData.put("repassword", scanner.nextLine());

        while (passwordsDoNotMatch(userData)){
            System.out.println("Password:");
            userData.put("password", scanner.nextLine());
            System.out.println("Reintroduce the password:");
            userData.put("repassword", scanner.nextLine());
        }

        return userData;
    }

//  EDIT NAME
    public void editName(User user, String name){
        user.setName(name);
    }
    public void editName(User user) {
        System.out.println("New name: ");
        user.setName(scanner.nextLine());
    }
//  EDIT PHONE NUMBER
    public void editPhoneNumber(User user, String phoneNumber){
        user.setPhoneNumber(phoneNumber);
    }
    public void editPhoneNumber(User user){
        System.out.println("New phone number: ");
        user.setPhoneNumber(scanner.nextLine());
    }
//  EDIT EMAIL
    public void editEmail(User user){
        System.out.println("New email:");
        user.setEmail(scanner.nextLine());
    }
//  EDIT PASSWORD
    public void editPassword(User user){
        System.out.println("Current password:");
        if (!user.getPassword().equals(scanner.nextLine())){
            System.out.println("Incorrect password.");
            return;
        }

        System.out.println("New password:");
        String newPassword = scanner.nextLine();
        System.out.println("Introduce the password again:");
        String reNewPassword = scanner.nextLine();

        while (!newPassword.equals(reNewPassword)){
            System.out.println("Incorrect password.\n");
            System.out.println("New password:");
            newPassword = scanner.nextLine();
            System.out.println("Introduce the password again:");
            reNewPassword = scanner.nextLine();
        }

        user.setPassword(newPassword);
    }
}
