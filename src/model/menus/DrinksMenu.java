package model.menus;

import model.products.Dish;
import model.products.Drink;

import java.util.ArrayList;
import java.util.List;

public class DrinksMenu extends Menu{
    private List<Drink> drinks;

    public DrinksMenu(){
        super();
        this.drinks = new ArrayList<Drink>();
    }
    public DrinksMenu(List<String> categories, List<Drink> drinks) {
        super(categories);
        this.drinks = drinks;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    @Override
    public String toString(){
        String prods = "";

        for (Drink d : drinks){
            prods += d.toString() + "\n----------------\n";
        }

        return "Categories: " + super.toString() + '\n' +
                "Drinks: \n" +  prods;
    }
}
