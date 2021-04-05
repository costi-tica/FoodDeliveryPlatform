package model.products;

import java.util.Scanner;

public abstract class Product {
    protected int id;
    protected String name;
    protected double price;
    protected int quantity;
    protected String unit;

    public Product(int id, String name, double price, int quantity, String unit) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Product Id: " + id + '\n' +
                "Product name: " + name + '\n' +
                "Price: " + price + '\n' +
                "Quantity: " + quantity + " " + unit;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
