package service;

import main.app.AppData;
import model.*;
import model.products.*;
import model.users.ResOwner;
import model.users.User;
import service.ReadWriteServices.RWProductService;

import java.util.*;

public final class RestaurantService {
    private final Scanner scanner;

    public RestaurantService(){
        this.scanner = new Scanner(System.in);
    }

//  GET RESTAURANT SCANNER DATA
    public String getRestaurantScannerData(){
        System.out.println("restaurant name:");
        return scanner.nextLine();
    }
    public Restaurant getRestaurantById(int id){
        return AppData.getInstance()
                .getRestaurants()
                .stream()
                .filter(res -> res.getId() == id)
                .findFirst()
                .orElse(null);
    }
    public ResOwner getRestaurantOwner(Restaurant res){
        return (ResOwner) AppData.getInstance()
                .getUsers()
                .stream()
                .filter(user -> user instanceof ResOwner && ((ResOwner) user).getOwnedRestaurant().equals(res))
                .findFirst()
                .orElse(null);
    }

//  SHOW
    public void showMenu(Restaurant res){
        System.out.println("MENU: \n\n" + res.getMenu().toString());
    }

//  ADD DISH TO MENU
    public void addDish(Restaurant res, Dish dish, String category){
        Map<String, List<Dish>> dishes = res.getMenu().getDishes();
        if (!dishes.containsKey(category)) {
            addDishCategory(res, category);
        }
        dishes.get(category).add(dish);
    }
    public void newDish(Restaurant res){
        Map<String, List<Dish>> dishes = res.getMenu().getDishes();

        System.out.println("Denumirea dish-ului: ");
        String name = scanner.nextLine();
        System.out.println("Categoria din care face parte: ");
        System.out.println("Optiuni: " + dishes.keySet() + " sau tasteaza nume_categorie_noua");
        String category = scanner.nextLine();

        System.out.println("Pretul: ");
        double price = scanner.nextDouble();
        System.out.println("Cantitatea per portie: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrediente: (pe o linie cu / intre ingrediente) ");
        String[] ingredients = scanner.nextLine().split("/");


        Dish dish = new Dish.Builder()
                .withId(res.getNextProdId())
                .withName(name)
                .withPrice(price)
                .withQuantity(quantity)
                .withIngredients(ingredients)
                .withGramsAsUnit()
                .build();

        addDish(res, dish, category);
        RWProductService.getInstance().write(getRestaurantOwner(res), dish, category); //
    }
//  ADD DRINK TO MENU
    public void addDrink(Restaurant res, Drink drink, String category){
        Map<String, List<Drink>> drinks = res.getMenu().getDrinks();
        if (!drinks.containsKey(category)){
            addDrinkCategory(res, category);
        }
        drinks.get(category).add(drink);
    }
    public void newDrink(Restaurant res){
        Map<String, List<Drink>> drinks = res.getMenu().getDrinks();

        System.out.println("Denumirea bauturii: ");
        String name = scanner.nextLine();
        System.out.println("Categoria din care face parte: ");
        System.out.println("Optiuni: " + drinks.keySet() + " sau tasteaza nume_categorie_noua");
        String category = scanner.nextLine();

        System.out.println("Pretul: ");
        double price = scanner.nextDouble();
        System.out.println("Cantitatea per sticla/pahar: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        Drink drink = new Drink.Builder()
                .withId(res.getNextProdId())
                .withName(name)
                .withPrice(price)
                .withQuantity(quantity)
                .withMillilitersAsUnit()
                .build();

        addDrink(res, drink, category);
        RWProductService.getInstance().write(getRestaurantOwner(res), drink, category);
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
