package model.products;

import jdk.jfr.Percentage;

public class Drink extends Product {
    public Drink(int id, String name, String category, double price, int quantity) {
        super(id, name, category, price, quantity, "ml");
    }

    @Override
    public String toString(){
        return super.toString();
    }
}
