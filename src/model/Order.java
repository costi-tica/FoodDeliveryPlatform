package model;

import model.products.Product;
import model.users.Client;
import model.users.Courier;

import java.util.List;

public final class Order {
    private final int id;
    private final Client client;
    private final Restaurant restaurant;
    private final Courier courier;
    private List<Product> products;
    private final double totalPrice;
    private final int estimatedTime; //minutes

    public Order(int id, Client client, Restaurant restaurant, Courier courier, List<Product> products) {
        this.id = id;
        this.courier = courier;
        this.restaurant = restaurant;
        this.client = client;
        this.products = products;
        this.totalPrice = calcPrice();
        this.estimatedTime = calcEstimatedTime();
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

    public double calcPrice(){
        double sum = 0;
        for (Product prod : products){
            sum += prod.getPrice();
        }
        return sum;
    }

    public int calcEstimatedTime(){
        return 0;
    }
}
