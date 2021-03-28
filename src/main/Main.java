package main;

import model.Restaurant;
import model.users.Client;
import model.users.Courier;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Application app = new Application();
        Scanner scanner = new Scanner(System.in);

        int option;
        while (true){
            System.out.println("""
                Choose:\s
                1) Add new client
                2) Add new courier
                3) Add new restaurant
                4) Create new order
                5) Show all clients
                6) Show all couriers
                7) Show all restaurants
                8) Show all orders
                9) Search for a client
                10) Search for a courier
                11) Search for a restaurant
                12) Exit
                OPTION:""");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> app.addClient();
                case 2 -> app.addCourier();
                case 3 -> app.addRestaurant();
                case 4 -> app.addOrder();
                case 5 -> app.showClients();
                case 6 -> app.showCouriers();
                case 7 -> app.showRestaurants();
                case 8 -> app.showOrders();
                case 9 -> app.searchFor(new Client());
                case 10 -> app.searchFor(new Courier());
                case 11 -> app.searchFor(new Restaurant());
                case 12 -> System.exit(0);
            }
        }
    }
}
