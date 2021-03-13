package main;

import model.Restaurant;

public class Main {
    public static void main(String[] args) {
        Application app = new Application();

        System.out.println("Adauga client");
        app.addClient();
        System.out.println("Adauga curier");
        app.addCourier();
        System.out.println("Adauga restaurant");
        app.addRestaurant();
        Restaurant resBristo = app.getRestaurantByName("Bristo");
        System.out.println("Adauga mancare: ");
        app.restaurantService.addDish(resBristo);
        app.showRestaurants();
//        resService.addDrink(resBristo);
//        app.showRestaurants();
//        resService.addReview(resBristo, app.getClient("Costi Tica"));
//        resService.addReview(resBristo, app.getClient("Costi Tica"));
//        System.out.println(resBristo + "\n" + resBristo.getReviews().get(0) + '\n' + resBristo.getReviews().get(1));
        System.out.println("Adauga comanda: ");
        app.addOrder();
        System.out.println("Orders: \n");
        app.showOrders();
    }
}
