package model;

import java.util.ArrayList;
import java.util.List;

public final class Restaurant {
    private int id;
    private String name;
    private Address address;
    private Menu menu;
    private List<Review> reviews;
    private int nextProdId;

    public Restaurant() {}

    public static class Builder {
        private final Restaurant res = new Restaurant();

        public Builder withId(int id){
            res.setId(id);
            return this;
        }
        public Builder withName(String name){
            res.setName(name);
            return this;
        }
        public Builder withAddress(Address address){
            res.setAddress(address);
            return this;
        }
        public Builder withReviews(List<Review> reviews){
            res.setReviews(reviews);
            return this;
        }
        public Builder withNoReviews() {
            res.setReviews(new ArrayList<>());
            return this;
        }
        public Builder withMenu(Menu menu){
            res.setMenu(menu);
            return this;
        }
        public Builder withEmptyMenu(){
            res.setMenu(new Menu());
            return this;
        }
        public Restaurant build(){
            return res;
        }
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
        return reviews.stream()
                .mapToInt(Review::getNumOfStars)
                .average()
                .orElse(0.0);
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
