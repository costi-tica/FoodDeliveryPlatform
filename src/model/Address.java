package model;

public final class Address {
    private String city;
    private String street;
    private int number;
    private String additionalInfo;

    public Address() {}

    public static class Builder{
        private final Address address = new Address();

        public Builder withCity(String city){
            address.setCity(city);
            return this;
        }
        public Builder withStreet(String street){
            address.setStreet(street);
            return this;
        }
        public Builder withNumber(int number){
            address.setNumber(number);
            return this;
        }
        public Builder withAdditionalInfo(String info){
            address.setAdditionalInfo(info);
            return this;
        }
        public Address build(){
            return address;
        }
    }

    @Override
    public String toString() {
        return "City: " + city + "\n" +
                "Street: " + street + "\n" +
                "Number: " + Integer.toString(number) + '\n' +
                "Additional info: " + additionalInfo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

}
