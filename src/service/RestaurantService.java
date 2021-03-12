package service;

import model.*;
import model.menus.DishesMenu;
import model.menus.DrinksMenu;
import model.products.*;
import model.users.Client;

import java.util.List;
import java.util.Scanner;

public class RestaurantService {
    private final Scanner scanner;

    public RestaurantService(){
        scanner = new Scanner(System.in);
    }
//  ADD

//  ADD REVIEW FOR RESTAURANT
    public void addReview(Restaurant res, Review review){
        res.getReviews().add(review);
    }
    public void addReview(Restaurant res, Client client){
        Review review = new Review();
        review.setFields(scanner);
        review.setUserId(client.getId());

        addReview(res, review);
    }
//  ADD DISH TO MENU
    public void addDish(Restaurant res, Dish dish){
        res.getDishesMenu().getDishes().add(dish);
    }
    public void addDish(Restaurant res){
        DishesMenu dishesMenu = res.getDishesMenu();

        System.out.println("Denumirea dish-ului: ");
        String name = scanner.nextLine();
        System.out.println("Categoria din care face parte: ");
        System.out.println("Optiuni: " + dishesMenu.getCategories() + " sau tasteaza nume_categorie_noua");
        String category = scanner.nextLine();

        if (!dishesMenu.getCategories().contains(category)) {
            addDishCategory(res, category);
        }

        System.out.println("Pretul: ");
        double price = scanner.nextDouble();
        System.out.println("Cantitatea per portie: ");
        int quantity = scanner.nextInt();
        System.out.println("Ingrediente: (pe o linie cu / intre ingrediente) ");
        scanner.nextLine();
        String ingredients = scanner.nextLine();

        Dish dish = new Dish(res.getNextProdId(), name, category,
                             price, quantity, ingredients.split("/"));
        addDish(res, dish);
    }
//  ADD DRINK TO MENU
    public void addDrink(Restaurant res, Drink drink){
        res.getDrinksMenu().getDrinks().add(drink);
    }
    public void addDrink(Restaurant res){
        DrinksMenu drinksMenu = res.getDrinksMenu();

        System.out.println("Denumirea bauturii: ");
        String name = scanner.nextLine();
        System.out.println("Categoria din care face parte: ");
        System.out.println("Optiuni: " + drinksMenu.getCategories() + " sau tasteaza nume_categorie_noua");
        String category = scanner.nextLine();

        if (!drinksMenu.getCategories().contains(category)){
            addDrinkCategory(res, category);
        }

        System.out.println("Pretul: ");
        double price = scanner.nextDouble();
        System.out.println("Cantitatea per sticla/pahar: ");
        int quantity = scanner.nextInt();

        Drink drink = new Drink(res.getNextProdId(),name, category, price, quantity);
        addDrink(res, drink);
    }
//  ADD FOOD CATEGORY TO MENU
    public void addDishCategory(Restaurant res, String category){
        res.getDishesMenu().getCategories().add(category);
    }
    public void addDishCategory(Restaurant res){
        System.out.println("Denumirea categoriei: ");
        String category = scanner.nextLine();

        addDishCategory(res, category);
    }
//  ADD DRINK CATEGORY TO MENU
    public void addDrinkCategory(Restaurant res, String category) {
        res.getDrinksMenu().getCategories().add(category);
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

//  EDIT

//  EDIT RATING
    public void editReview(Restaurant res, Client client){
        List<Review> reviews = res.getReviews();
        for (Review r : reviews){
            if (r.getUserId() == client.getId()){
                System.out.println("Your review: \n" + r.toString() + '\n');
                r.updateFields(scanner);
                break;
            }
        }
    }
//  EDIT DISH FROM MENU
    public void editDish(Restaurant res, int dishId){
        List<Dish> dishes = res.getDishesMenu().getDishes();
        Dish toEdit = null;
        for (Dish d : dishes){
            if (d.getId() == dishId){
                toEdit = d;
                break;
            }
        }
        if (toEdit == null) {
            System.out.println("Dish does not exist.");
            return;
        }

        System.out.println("Produsul actual: \n" + toEdit.toString() + '\n');
        System.out.println("Ce campuri doriti sa modificati? (cu / intre ele)");

        toEdit.updateFields(scanner, scanner.nextLine().split("/"));
    }
//  EDIT DRINK FROM MENU
    public void editDrink(Restaurant res, int drinkId){
        List<Drink> drinks = res.getDrinksMenu().getDrinks();
        Drink toEdit = null;
        for (Drink d : drinks){
            if (d.getId() == drinkId){
                toEdit = d;
                break;
            }
        }

        if (toEdit == null) {
            System.out.println("Drink does not exist.");
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

//  DELETE

//  DELETE REVIEW
    public void deleteReview(Restaurant res, Client client){
        List<Review> reviews = res.getReviews();
        Review toDelete = null;
        for (Review r : reviews){
            if (r.getUserId() == client.getId()){
                toDelete = r;
                break;
            }
        }
        if (toDelete != null) {
            reviews.remove(toDelete);
        }
    }
//  DELETE DISH FROM MENU
    public void deleteDish(Restaurant res, int dishId){
        List<Dish> dishes = res.getDishesMenu().getDishes();
        Dish toDelete = null;
        for (Dish d : dishes){
            if (d.getId() == dishId){
                toDelete = d;
                break;
            }
        }
        if (toDelete != null){
            dishes.remove(toDelete);
        }
    }
//  DELETE DRINK FROM MENU
    public void deleteDrink(Restaurant res, int drinkId){
        List<Drink> drinks = res.getDrinksMenu().getDrinks();
        Drink toDelete = null;
        for (Drink d : drinks){
            if (d.getId() == drinkId){
                toDelete = d;
                break;
            }
        }
        if (toDelete != null){
            drinks.remove(toDelete);
        }
    }
//  DELETE FOOD CATEGORY FROM MENU
    public void deleteDishCategory(Restaurant res, String category){
        res.getDishesMenu().getCategories().remove(category.toLowerCase());
    }
//  DELETE DRINK CATEGORY FROM MENU
    public void deleteDrinkCategory(Restaurant res, String category){
        res.getDrinksMenu().getCategories().remove(category.toLowerCase());
    }
}
