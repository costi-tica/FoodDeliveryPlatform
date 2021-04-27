package app_core;

import model.Order;
import model.Restaurant;
import model.users.User;

import java.util.ArrayList;
import java.util.List;

public final class AppData {
    List<User> users;
    List<Restaurant> restaurants;
    List<Order> orders;
    boolean dataLoaded;

    private static AppData INSTANCE;

    private static int nextUserId, nextRestaurantId, nextOrderId;

    private AppData() {
        this.users = new ArrayList<>();
        this.restaurants = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.dataLoaded = false;
    }

    public static AppData getInstance() {
        if (INSTANCE == null) INSTANCE = new AppData();
        return INSTANCE;
    }

    public List<User> getUsers() {
        return users;
    }
    public List<Restaurant> getRestaurants() {
        return restaurants;
    }
    public List<Order> getOrders() {
        return orders;
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
