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
    private Courier courier;
    private List<Product> products;
    private double totalPrice;
    private final Date date;
    private Status status = Status.PLACED;

    public enum Status {
        PLACED,
        SHIPPING,
        COMPLETED
    }

    public Order(int id, Client client, Restaurant restaurant, List<Product> products) {
        this.id = id;
        this.client = client;
        this.restaurant = restaurant;
        this.products = products;
        this.date = new Date();
    }

    @Override
    public String toString(){
        return "Order Id: " + id + '\n'+
                "Client: " + client.toString() + "\n\n" +
                "Restaurant: " + restaurant.toString() + "\n\n"+
                "Total price: " + totalPrice + '\n'+
                "Status: " + status;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }
}
