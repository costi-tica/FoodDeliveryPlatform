package model.users;

import model.Review;

import java.util.ArrayList;
import java.util.List;

public class Courier extends User{
    private List<String> transportMeans;

    public Courier(){}
    public Courier(int id){
        super(id);
    }
    public Courier(int id, String name, String phoneNumber, List<String> transportMeans) {
        super(id, name, phoneNumber);
        this.transportMeans = transportMeans;
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
