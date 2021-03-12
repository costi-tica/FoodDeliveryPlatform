package main;

import model.Address;
import model.Review;
import model.Restaurant;
import model.menus.DishesMenu;
import model.menus.DrinksMenu;
import model.products.Dish;
import model.products.Drink;
import service.RestaurantService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Address address = new Address("Craiova", "Doljului", 5, "");
        List<Review> reviews = new ArrayList<>();

        List<String> dishCategs = new ArrayList<String>();
        dishCategs.add("ciorbe");
        dishCategs.add("desert");
        List<Dish> dishes = new ArrayList<Dish>();
        DishesMenu dishMenu = new DishesMenu(dishCategs, dishes);

        List<String> drinkCategs = new ArrayList<String>();
        drinkCategs.add("Vin");
        drinkCategs.add("Suc");
        List<Drink> drinks = new ArrayList<Drink>();
        DrinksMenu drinkMenu = new DrinksMenu(drinkCategs, drinks);

        Restaurant res = new Restaurant(0, "Dunarea", address, reviews, dishMenu, drinkMenu);

        System.out.println(res);

        System.out.println("*************************");

        RestaurantService rs = new RestaurantService();
        rs.addDishCategory(res);
        rs.addDishCategory(res);
        rs.addDrinkCategory(res);

        System.out.println(res);


    }
}
