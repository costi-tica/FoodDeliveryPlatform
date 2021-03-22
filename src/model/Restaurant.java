package model;

import java.util.ArrayList;
import java.util.List;

public final class Restaurant {
    private final int id;
    private String name;
    private Address address;
    private Menu menu;
    private List<Review> reviews;
    private int nextProdId;

    public Restaurant(int id, String name, Address address){
        this.id = id;
        this.name = name;
        this.address = address;
        this.menu = new Menu();
        this.reviews = new ArrayList<>();
    }
    public Restaurant(int id, String name, Address address, List<Review> reviews, Menu menu) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.reviews = reviews;
        this.menu = menu;
    }

    public int getNextProdId() {
        return nextProdId++;
    }

    @Override
    public String toString(){
        double rating = getRating();
        String ratingMessage = rating == 0 ? "No reviews" : Double.toString(rating);
        return  "Restaurant Id: " + id + '\n' +
                "Name: " + name + "\n\n" +
                "Address: \n" + address + "\n\n"+
                "Rating: " + ratingMessage;
    }

    public double getRating(){
        int s = 0;
        for (Review r : reviews){
            s += r.getNumOfStars();
        }
        return reviews.size() >0 ? s * 1.0 / reviews.size() : 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public Menu getMenu() {
        return menu;
    }
}
