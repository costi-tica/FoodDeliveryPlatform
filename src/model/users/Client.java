package model.users;

import model.Address;

import java.util.Scanner;

public class Client extends User{
    private Address address;

    public Client(int id){
        super(id);
    }
    public Client(int id, String name, String phoneNumber, Address address) {
        super(id, name, phoneNumber);
        this.address = address;
    }

    public void setFields(Scanner scanner){
        super.setFields(scanner);
        System.out.println("Address:\n");
        this.address = new Address();
        address.setFields(scanner);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
