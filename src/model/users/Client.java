package model.users;

import model.Address;

import java.util.Scanner;

public class Client extends User{
    private Address address;

    public Client(){}
    public Client(int id){
        super(id);
    }
    public Client(int id, String name, String phoneNumber, Address address) {
        super(id, name, phoneNumber);
        this.address = address;
    }

    @Override
    public String toString(){
        return super.toString() + '\n' +
                "Address: \n" + this.address.toString();
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
