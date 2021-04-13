package model;

import model.products.Dish;
import model.products.Drink;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Menu {
    private final Map<String, List<Dish>> dishes;
    private final Map<String, List<Drink>> drinks;

    public Menu(){
        this.drinks = new HashMap<>();
        this.dishes = new HashMap<>();
    }

    @Override
    public String toString(){
        StringBuilder dishesToString = new StringBuilder();

        for (String categ: dishes.keySet()){
            dishesToString.append(categ.toUpperCase()).append(":\n");
            for (Dish d : dishes.get(categ)){
                dishesToString.append(d.toString()).append("\n\n");
            }
        }

        StringBuilder drinksToString = new StringBuilder();

        for (String categ: drinks.keySet()){
            drinksToString.append(categ.toUpperCase()).append(":\n");
            for (Drink d : drinks.get(categ)){
                drinksToString.append(d.toString()).append("\n\n");
            }
        }

        return "DISHES: \n" + dishesToString +
                "DRINKS: \n" + drinksToString;
    }

    public Map<String, List<Dish>> getDishes() {
        return dishes;
    }

    public Map<String, List<Drink>> getDrinks() {
        return drinks;
    }
}
