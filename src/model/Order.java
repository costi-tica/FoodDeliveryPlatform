package model;

import model.products.Product;
import model.users.Client;
import model.users.Courier;

import java.util.Date;
import java.util.List;

public final class Order {
    private final int id;
    private final Client client;
    private final Restaurant restaurant;
    private final Courier courier;
    private List<Product> products;
    private double totalPrice;
    private final Date date;
    private int estimatedTime; //minutes

    public Order(int id, Client client, Restaurant restaurant, Courier courier, List<Product> products) {
        this.id = id;
        this.courier = courier;
        this.restaurant = restaurant;
        this.client = client;
        this.products = products;
        this.date = new Date();
    }

    @Override
    public String toString(){
        return "Order Id: " + id + '\n'+
                "Client: " + client.toString() + "\n\n" +
                "Restaurant: " + restaurant.toString() + "\n\n"+
                "Courier: " + courier.toString() + "\n\n" +
                "Total price: " + totalPrice + '\n' +
                "Estimated time: " + estimatedTime;
    }

    public Client getClient() {
        return client;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Courier getCourier() {
        return courier;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }
}
