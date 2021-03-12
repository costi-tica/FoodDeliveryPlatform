package model.products;

import java.util.Scanner;

public abstract class Product {
    protected int id;
    protected String name;
    protected String category;
    protected double price;
    protected int quantity;
    protected String unit;

    public Product(int id, String name, String category, double price, int quantity, String unit) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Id: " + id + '\n' +
                "Product: " + name + '\n' +
                "Category: " + category + '\n' +
                "Price: " + price + '\n' +
                "Quantity: " + quantity + " " + unit;
    }

//    public void setFields(Scanner scanner){
//
//    }

    public void updateFields(Scanner scanner, String[] fields){
        for (String field : fields){
            switch (field.toLowerCase()){
                case "name":
                    System.out.println("New name: ");
                    this.name = scanner.nextLine();
                    break;
                case "category":
                    System.out.println("New category: ");
                    this.category = scanner.nextLine();
                    break;
                case "price":
                    System.out.println("New price: ");
                    this.price = scanner.nextDouble();
                    break;
                case "quantity":
                    System.out.println("New quantity/serving: ");
                    this.quantity = scanner.nextInt();
                    break;
            }
        }
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
