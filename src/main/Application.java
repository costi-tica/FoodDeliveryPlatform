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


public final class Application {
   private List<Client> clients;
   private List<Courier> couriers;
   private List<Restaurant> restaurants;
   private List<Order> orders;
   private int nextClientId, nextCourierId, nextRestaurantId, nextOrderId;
   RestaurantService restaurantService;
   UserService userService;

   public Application(){
      this.clients = new ArrayList<>();
      this.couriers = new ArrayList<>();
      this.restaurants = new ArrayList<>();
      this.orders = new ArrayList<>();
      this.restaurantService = new RestaurantService();
      this.userService = new UserService();
   }

// ORDER OPTIONS MENU
   private void orderOptionsMenu(String forName){
      System.out.println(forName + " does not exist. Choose:");
      System.out.println("\t1) Type the name again");
      System.out.println("\t2) Show all " + forName + 's');
      System.out.println("\t3) Exit");
      System.out.println("Option: ");
   }

// GET
   public Restaurant getRestaurantByName(String name){
      for (Restaurant r : restaurants){
         if (r.getName().equalsIgnoreCase(name)){
            return r;
         }
      }
      return null;
   }
   public Client getClientByName(String name){
      for (Client c : clients){
         if (c.getName().equalsIgnoreCase(name)){
            return c;
         }
      }
      return null;
   }
   public Courier getCourierByName(String name){
      for (Courier c : couriers){
         if (c.getName().equalsIgnoreCase(name)){
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

// ADD CLIENT
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
               clientFound = getClientByName(scanner.nextLine());
               break;
            case 2:
               showClients();
               break;
            case 3:
               return;
         }
         if (clientFound == null){
            orderOptionsMenu("Client");
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
               resFound = getRestaurantByName(scanner.nextLine());
               break;
            case 2:
               showRestaurants();
               break;
            case 3:
               return;
         }
         if (resFound == null){
            orderOptionsMenu("Restaurant");
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
               courierFound = getCourierByName(scanner.nextLine());
               break;
            case 2:
               showCouriers();
               break;
            case 3:
               return;
         }
         if (courierFound == null){
            orderOptionsMenu("Courier");
            option = scanner.nextInt();
            scanner.nextLine();
         }
      }

      System.out.println("Product Ids: ('/' between ids)");
      List<Product> prods = new ArrayList<>();
      for (String id : scanner.nextLine().split("/")){
         Product prod = restaurantService.getProductById(resFound, Integer.parseInt(id));
         if (prod != null) prods.add(prod);
      }

      Order order = new Order(nextOrderId++, clientFound, resFound, courierFound, prods);
      this.orders.add(order);
   }
}
