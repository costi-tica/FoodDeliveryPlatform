package main;

import model.Address;
import model.Order;
import model.Restaurant;
import model.products.Product;
import model.users.Client;
import model.users.Courier;
import service.RestaurantService;
import service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Application {
   private List<Client> clients;
   private List<Courier> couriers;
   private List<Restaurant> restaurants;
   private List<Order> orders;
   private int nextClientId, nextCourierId, nextRestaurantId, nextOrderId;
   RestaurantService restaurantService;
   UserService userService;

   public Application(){
      this.clients = new ArrayList<Client>();
      this.couriers = new ArrayList<Courier>();
      this.restaurants = new ArrayList<Restaurant>();
      this.orders = new ArrayList<Order>();
      this.restaurantService = new RestaurantService();
      this.userService = new UserService();
   }

// GET
   public Restaurant getRestaurantByName(String name){
      for (Restaurant r : restaurants){
         if (r.getName().equals(name)){
            return r;
         }
      }
      return null;
   }
   public Client getClientByName(String name){
      for (Client c : clients){
         if (c.getName().equals(name)){
            return c;
         }
      }
      return null;
   }

// SHOW
   public void showClients(){
      for (Client c : clients){
         System.out.println(c);
         System.out.println("_____________________");
      }
   }
   public void showCouriers(){
      for (Courier c : couriers){
         System.out.println(c);
         System.out.println("_____________________");
      }
   }
   public void showRestaurants(){
      for (Restaurant r : restaurants){
         System.out.println(r);
         System.out.println("_____________________");
      }
   }
   public void showOrders(){
      for(Order o : orders){
         System.out.println(o);
         System.out.println("_____________________");
      }
   }

// ADD

//   ADD CLIENT
   public void addClient(){
      Scanner scanner = new Scanner(System.in);
      Client client = new Client(nextClientId++);

      client.setFields(scanner);
      this.clients.add(client);
   }
// ADD COURIER
   public void addCourier(){
      Scanner scanner = new Scanner(System.in);
      Courier courier = new Courier(nextCourierId++);

      courier.setFields(scanner);
      this.couriers.add(courier);
   }
// ADD RESTAURANT
   public void addRestaurant(){
      Scanner scanner = new Scanner(System.in);

      System.out.println("Name: ");
      String name = scanner.nextLine();

      System.out.println("Address: \n");
      Address address = new Address();
      address.setFields(scanner);

      Restaurant res = new Restaurant(nextRestaurantId++, name, address);
      this.restaurants.add(res);
   }
// ADD ORDER
   public void addOrder() {
      Scanner scanner = new Scanner(System.in);

      Client clientFound = null;
      int option = 1;
      String clientName;
      while (clientFound == null) {
         switch (option){
            case 1:
               System.out.println("Client: (name)");
               clientName = scanner.nextLine();
               for (Client client : clients) {
                  if (client.getName().equals(clientName)) {
                     clientFound = client;
                     break;
                  }
               }
               break;
            case 2:
               showClients();
               break;
            case 3:
               return;
         }
         if (clientFound == null){
            System.out.println("Client does not exist. Choose:");
            System.out.println("\t1) Type the name again");
            System.out.println("\t2) Show all clients");
            System.out.println("\t3) Exit");
            System.out.println("Optiune: ");
            option = scanner.nextInt();
            scanner.nextLine();
         }
      }


      Restaurant resFound = null;
      option = 1;
      String resName;
      while (resFound == null) {
         switch (option){
            case 1:
               System.out.println("Restaurant: (name)");
               resName = scanner.nextLine();
               for (Restaurant r : restaurants) {
                  if (r.getName().equals(resName)) {
                     resFound = r;
                     break;
                  }
               }
               break;
            case 2:
               showRestaurants();
               break;
            case 3:
               return;
         }
         if (resFound == null){
            System.out.println("Restaurant does not exist. Choose:");
            System.out.println("\t1) Type the name again");
            System.out.println("\t2) Show all restaurants");
            System.out.println("\t3) Exit");
            System.out.println("Optiune: ");
            option = scanner.nextInt();
            scanner.nextLine();
         }
      }

      Courier courierFound = null;
      option = 1;
      String courierName;
      while (courierFound == null) {
         switch (option){
            case 1:
               System.out.println("Courier: (name)");
               courierName = scanner.nextLine();
               for (Courier c : couriers) {
                  if (c.getName().equals(courierName)) {
                     courierFound = c;
                     break;
                  }
               }
               break;
            case 2:
               showCouriers();
               break;
            case 3:
               return;
         }
         if (courierFound == null){
            System.out.println("Courier does not exist. Choose:");
            System.out.println("\t1) Type the name again");
            System.out.println("\t2) Show all couriers");
            System.out.println("\t3) Exit");
            System.out.println("Optiune: ");
            option = scanner.nextInt();
            scanner.nextLine();
         }
      }

      System.out.println("Product Ids: ('/' between ids)");
      List<Product> prods = new ArrayList<Product>();
      for (String id : scanner.nextLine().split("/")){
         Product prod = restaurantService.getProductById(resFound, Integer.parseInt(id));
         if (prod != null) prods.add(prod);
      }

      Order order = new Order(nextOrderId++, clientFound, resFound, courierFound, prods);
      this.orders.add(order);
   }
}
