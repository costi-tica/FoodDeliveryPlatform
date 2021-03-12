package model.users;

import model.Address;

public class Client extends User{
    private Address address;

    public Client(int id, String name, String phoneNumber, Address address) {
        super(id, name, phoneNumber);
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
