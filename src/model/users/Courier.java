package model.users;

import java.util.List;

public class Courier extends User{
    private List<String> transportMeans;

    public Courier(){}

    public static class Builder{
        private final Courier courier = new Courier();

        public Builder withId(int id){
            courier.setId(id);
            return this;
        }
        public Builder withName(String name){
            courier.setName(name);
            return this;
        }
        public Builder withPhoneNumber(String phoneNumber){
            courier.setPhoneNumber(phoneNumber);
            return this;
        }
        public Builder withTransportMeans(List<String> transportMeans){
            courier.setTransportMeans(transportMeans);
            return this;
        }
        public Courier build(){
            return this.courier;
        }
    }

    @Override
    public String toString(){
        return super.toString() + '\n' +
                "Transport means: " + this.transportMeans.toString();

    }

    public List<String> getTransportMeans() {
        return transportMeans;
    }

    public void setTransportMeans(List<String> transportMeans) {
        this.transportMeans = transportMeans;
    }
}
