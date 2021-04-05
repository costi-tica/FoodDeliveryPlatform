package service;

import model.*;
import model.products.*;

import java.util.*;

public final class RestaurantService {
    private final Scanner scanner;

    public RestaurantService(){
        this.scanner = new Scanner(System.in);
    }

//  SET FIELDS
    public void setFields(Restaurant res){
        System.out.println("Name: ");
        res.setName(scanner.nextLine());
    }

//  SHOW
    public void showMenu(Restaurant res){
        System.out.println("MENU: \n\n" + res.getMenu().toString());
    }

//  ADD DISH TO MENU
    public void addDish(Restaurant res, Dish dish, String category){
        res.getMenu().getDishes().get(category).add(dish);
    }
    public void addDish(Restaurant res){
        Map<String, List<Dish>> dishes = res.getMenu().getDishes();

        System.out.println("Denumirea dish-ului: ");
        String name = scanner.nextLine();
        System.out.println("Categoria din care face parte: ");
        System.out.println("Optiuni: " + dishes.keySet() + " sau tasteaza nume_categorie_noua");
        String category = scanner.nextLine();

        if (!dishes.containsKey(category)) {
            addDishCategory(res, category);
        }

        System.out.println("Pretul: ");
        double price = scanner.nextDouble();
        System.out.println("Cantitatea per portie: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrediente: (pe o linie cu / intre ingrediente) ");
        String[] ingredients = scanner.nextLine().split("/");

        Dish dish = new Dish(res.getNextProdId(), name, price, quantity, ingredients);
        addDish(res, dish, category);
    }
//  ADD DRINK TO MENU
    public void addDrink(Restaurant res, Drink drink, String category){
        res.getMenu().getDrinks().get(category).add(drink);
    }
    public void addDrink(Restaurant res){
        Map<String, List<Drink>> drinks = res.getMenu().getDrinks();

        System.out.println("Denumirea bauturii: ");
        String name = scanner.nextLine();
        System.out.println("Categoria din care face parte: ");
        System.out.println("Optiuni: " + drinks.keySet() + " sau tasteaza nume_categorie_noua");
        String category = scanner.nextLine();

        if (!drinks.containsKey(category)){
            addDrinkCategory(res, category);
        }

        System.out.println("Pretul: ");
        double price = scanner.nextDouble();
        System.out.println("Cantitatea per sticla/pahar: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        Drink drink = new Drink(res.getNextProdId(),name, price, quantity);
        addDrink(res, drink, category);
    }
//  ADD FOOD CATEGORY TO MENU
    public void addDishCategory(Restaurant res, String category){
        List<Dish> emptyDishesList = new ArrayList<>();
        res.getMenu().getDishes().put(category, emptyDishesList);
    }
    public void addDishCategory(Restaurant res){
        System.out.println("Denumirea categoriei: ");
        String category = scanner.nextLine();

        addDishCategory(res, category);
    }
//  ADD DRINK CATEGORY TO MENU
    public void addDrinkCategory(Restaurant res, String category) {
        List<Drink> emptyDrinksList = new ArrayList<>();
        res.getMenu().getDrinks().put(category, emptyDrinksList);
    }
    public void addDrinkCategory(Restaurant res){
        System.out.println("Denumirea categoriei: ");
        String category = scanner.nextLine();

        addDrinkCategory(res, category);
    }


//  EDIT NAME
    public void editName(Restaurant res, String name){
        res.setName(name);
    }
    public void editName(Restaurant res){
        System.out.println("Intoduceti noul nume al restaurantului: ");
        res.setName(scanner.nextLine());
    }

//  DELETE FOOD CATEGORY FROM MENU
    public void deleteDishCategory(Restaurant res, String category){
        res.getMenu().getDishes().remove(category.toLowerCase());
    }
//  DELETE DRINK CATEGORY FROM MENU
    public void deleteDrinkCategory(Restaurant res, String category){
        res.getMenu().getDrinks().remove(category.toLowerCase());
    }
}
