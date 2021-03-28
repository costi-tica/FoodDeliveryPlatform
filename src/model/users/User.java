package model.users;

import java.util.Scanner;

public abstract class User {
    protected int id;
    protected String name;
    protected String phoneNumber;

    public User(){}
    public User(int id){
        this.id = id;
    }
    public User(int id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString(){
        return "Name: " + name + '\n' +
                "Phone number: " + phoneNumber;
    }

    public void setFields(Scanner scanner){
        System.out.println("Name: ");
        this.name = scanner.nextLine();

        System.out.println("Phone number: ");
        this.phoneNumber = scanner.nextLine();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
