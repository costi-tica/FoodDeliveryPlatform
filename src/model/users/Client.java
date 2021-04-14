package model.users;

import model.Address;

public final class Client extends User{
    private Address address;

    public Client(){}

    public static class Builder extends User.Builder<Client, Builder>{
        public Builder(){
            super(new Client());
        }

        @Override
        protected Builder getThis(){
            return this;
        }

        public Builder withAddress(Address address){
            this.user.setAddress(address);
            return this;
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
