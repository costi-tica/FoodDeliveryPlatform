package service;

import model.*;
import model.products.*;
import model.users.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public final class RestaurantService {
    private final Scanner scanner;

    public RestaurantService(){
        scanner = new Scanner(System.in);
    }

//  GET
    public Product getProductById(Restaurant res, int id){
        HashMap<String,List<Dish>> dishes = res.getMenu().getDishes();
        for (String categ : dishes.keySet()) for (Dish d : dishes.get(categ))
            if (d.getId() == id) return d;

        HashMap<String,List<Drink>> drinks = res.getMenu().getDrinks();
        for (String categ : drinks.keySet()) for (Drink d : drinks.get(categ))
            if (d.getId() == id) return d;

        return null;
    }
    public String getProductCategory(Restaurant res, int id){
        HashMap<String,List<Dish>> dishes = res.getMenu().getDishes();
        for (String categ : dishes.keySet()) for (Dish d : dishes.get(categ))
            if (d.getId() == id) return categ;

        HashMap<String,List<Drink>> drinks = res.getMenu().getDrinks();
        for (String categ : drinks.keySet()) for (Drink d : drinks.get(categ))
            if (d.getId() == id) return categ;

        return "";
    }

//  SHOW
    public void showMenu(Restaurant res){
        System.out.println("MENU: \n\n" + res.getMenu().toString());
    }

//  ADD REVIEW FOR RESTAURANT
    public void addReview(Restaurant res, Review review){
        res.getReviews().add(review);
    }
    public void addReview(Restaurant res, Client client){
        Review review = new Review();
        review.setFields(scanner);
        review.setClient(client);

        addReview(res, review);
    }
//  ADD DISH TO MENU
    public void addDish(Restaurant res, Dish dish, String category){
        res.getMenu().getDishes().get(category).add(dish);
    }
    public void addDish(Restaurant res){
        HashMap<String, List<Dish>> dishes = res.getMenu().getDishes();

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
        HashMap<String, List<Drink>> drinks = res.getMenu().getDrinks();

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
//  ADD ADDRESS
    public void addAddress(Restaurant res, Address address){
        res.setAddress(address);
    }
    public void addAddress(Restaurant res){
        Address address = new Address();
        address.setFields(scanner);

        res.setAddress(address);
    }

//  EDIT RATING
    public void editReview(Restaurant res, Client client){
        List<Review> reviews = res.getReviews();
        for (Review r : reviews){
            if (r.getClient().getId() == client.getId()){
                System.out.println("Your review: \n" + r.toString() + '\n');
                r.updateFields(scanner);
                break;
            }
        }
    }
//  EDIT PRODUCT FROM MENU
    public void editProduct(Restaurant res, int prodId){
        Product toEdit = getProductById(res, prodId);

        if (toEdit == null) {
            System.out.println("Product does not exist.");
            return;
        }

        System.out.println("Produsul actual: \n" + toEdit.toString() + '\n');
        System.out.println("Ce campuri doriti sa modificati? (cu / intre ele)");

        toEdit.updateFields(scanner, scanner.nextLine().split("/"));
    }
//  EDIT NAME
    public void editName(Restaurant res, String name){
        res.setName(name);
    }
    public void editName(Restaurant res){
        System.out.println("Intoduceti noul nume al restaurantului: ");
        res.setName(scanner.nextLine());
    }
//  EDIT ADDRESS
    public void editAddress(Restaurant res, Address address){
        res.setAddress(address);
    }
    public void editAddress(Restaurant res){
        Address address = res.getAddress();
        System.out.println("Adresa actuala: \n" + address.toString() + '\n');
        System.out.println("Ce campuri doriti sa modificati? (cu / intre)");

        address.updateFields(scanner, scanner.nextLine().split("/"));
    }

//  DELETE REVIEW
    public void deleteReview(Restaurant res, Client client){
        List<Review> reviews = res.getReviews();
        Review toDelete = null;
        for (Review r : reviews){
            if (r.getClient().getId() == client.getId()){
                toDelete = r;
                break;
            }
        }
        if (toDelete != null) {
            reviews.remove(toDelete);
        }
    }
//  DELETE DISH FROM MENU
    public void deleteProduct(Restaurant res, int prodId){
        Product toDelete = getProductById(res, prodId);
        String category = getProductCategory(res, prodId);

        if (toDelete != null){
            if (toDelete instanceof Dish)
                res.getMenu().getDishes().get(category).remove(toDelete);
            else if (toDelete instanceof Drink)
                res.getMenu().getDrinks().get(category).remove(toDelete);
        }
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
