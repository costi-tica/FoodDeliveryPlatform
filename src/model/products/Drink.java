package model.products;

import java.util.Scanner;

public class Drink extends Product {
    public Drink(int id, String name, double price, int quantity) {
        super(id, name, price, quantity, "ml");
    }

    @Override
    public String toString(){
        return super.toString();
    }
}
