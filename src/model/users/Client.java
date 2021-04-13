package model.users;

import model.Address;

public class Client extends User{
    private Address address;

    public Client(){}

    public static class Builder{
        private final Client client = new Client();

        public Builder withId(int id){
            client.setId(id);
            return this;
        }
        public Builder withName(String name){
            client.setName(name);
            return this;
        }
        public Builder withPhoneNumber(String phoneNumber){
            client.setPhoneNumber(phoneNumber);
            return this;
        }
        public Builder withAddress(Address address){
            client.setAddress(address);
            return this;
        }
        public Client build(){
            return this.client;
        }
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
