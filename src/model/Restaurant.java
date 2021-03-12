package model;

import model.menus.DrinksMenu;
import model.menus.DishesMenu;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private final int id;
    private String name;
    private Address address;
    private List<Review> reviews;
    private DishesMenu dishesMenu;
    private DrinksMenu drinksMenu;

    public Restaurant(int id, String name){
        this.id = id;
        this.name = name;
        this.reviews = new ArrayList<Review>();
        this.dishesMenu = new DishesMenu();
        this.drinksMenu = new DrinksMenu();
    }
    public Restaurant(int id, String name, Address address, List<Review> reviews, DishesMenu dishesMenu, DrinksMenu drinksMenu) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.reviews = reviews;
        this.dishesMenu = dishesMenu;
        this.drinksMenu = drinksMenu;
    }

    public double calcRating(){
        int s = 0;
        for (Review r : reviews){
            s += r.getNumOfStars();
        }
        return reviews.size() >0 ? s * 1.0 / reviews.size() : 0;
    }

    @Override
    public String toString(){
        double rating = calcRating();
        String ratingMessage = rating == 0 ? "No reviews" : Double.toString(rating);
        return  "Id: " + id + '\n' +
                "Name: " + name + "\n\n" +
                "Address: \n" + address + "\n\n"+
                "Rating: " + ratingMessage  + "\n\n" +
                "Food menu: \n" + dishesMenu + "\n" +
                "Drinks menu: \n" + drinksMenu;
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

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public DishesMenu getDishesMenu() {
        return dishesMenu;
    }

    public void setDishesMenu(DishesMenu dishesMenu) {
        this.dishesMenu = dishesMenu;
    }

    public DrinksMenu getDrinksMenu() {
        return drinksMenu;
    }

    public void setDrinksMenu(DrinksMenu drinksMenu) {
        this.drinksMenu = drinksMenu;
    }
}
