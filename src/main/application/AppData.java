package main.application;

import model.Order;
import model.Restaurant;
import model.users.User;

import java.util.ArrayList;
import java.util.List;

public class AppData {
    List<User> users;
    List<Restaurant> restaurants;
    List<Order> orders;

    private static int nextUserId, nextRestaurantId, nextOrderId;

    public AppData() {
        this.users = new ArrayList<>();
        this.restaurants = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }
    public void addRestaurant(Restaurant res) {
        restaurants.add(res);
    }
    public void addOrder(Order order) {
        orders.add(order);
    }

    public int getNextUserId() {
        return nextUserId++;
    }
    public int getNextOrderId() {
        return nextOrderId++;
    }
    public int getNextRestaurantId() {
        return nextRestaurantId++;
    }
}
