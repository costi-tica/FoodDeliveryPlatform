package main;

import model.Address;
import model.Order;
import model.Restaurant;
import model.products.Product;
import model.users.Client;
import model.users.Courier;
import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Application {
   private List<Client> clients;
   private int nextClientId;
   private List<Courier> couriers;
   private int nextCourierId;
   private List<Restaurant> restaurants;
   private int nextRestaurantId;
   private List<Order> orders;
   private int nextOrderId;


   public Application(){
      this.clients = new ArrayList<Client>();
      this.couriers = new ArrayList<Courier>();
      this.restaurants = new ArrayList<Restaurant>();
      this.orders = new ArrayList<Order>();
   }

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

// ADD

//   ADD CLIENT
//   public void addClient(Client client){
//      this.clients.add(client);
//   }
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

      Restaurant res = new Restaurant(nextRestaurantId++, name);
      this.restaurants.add(res);
   }
// ADD ORDER
   public void addOrder() {
      Scanner scanner = new Scanner(System.in);

      System.out.println("Client: (name)");
      Client clientFound = null;
      int option = 1;
      String clientName;
      while (clientFound == null) {
         switch (option){
            case 1:
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
            option = scanner.nextInt();
         }
      }

      System.out.println("Restaurant: (name)");
      Restaurant resFound = null;
      option = 1;
      String resName;
      while (resFound == null) {
         switch (option){
            case 1:
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
            option = scanner.nextInt();
         }
      }

      System.out.println("Restaurant: (name)");
      Courier courierFound = null;
      option = 1;
      String courierName;
      while (courierFound == null) {
         switch (option){
            case 1:
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
            option = scanner.nextInt();
         }
      }

      System.out.println("Product Ids: ('/' between ids)");
      List<Product> prods = new ArrayList<Product>();
      // TO BE CONTINUED
//      for ()...
   }
}
