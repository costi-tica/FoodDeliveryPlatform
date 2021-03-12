package model;

import model.products.Product;
import model.users.Client;
import model.users.Courier;

import java.util.List;

public class Order {
    private final int id;
    private final double price;
    private final Courier courier;
    private final Restaurant restaurant;
    private final Client client;
    private List<Product> products;
    private final int estimatedTime; //minutes

    public Order(int id, double price, Courier courier, Restaurant restaurant, Client client, List<Product> products) {
        this.id = id;
        this.price = price;
        this.courier = courier;
        this.restaurant = restaurant;
        this.client = client;
        this.products = products;
        this.estimatedTime = calcEstimatedTime();
    }

    public int calcEstimatedTime(){
        return 0;
    }
}
