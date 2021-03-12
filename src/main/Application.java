package main;

import model.Address;
import model.Order;
import model.Restaurant;
import model.users.Client;
import model.users.Courier;

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

// ADD
//   public void addClient(Client client){
//      this.clients.add(client);
//   }
   public void addClient(){
      Scanner scanner = new Scanner(System.in);
      System.out.println("Name: ");

      String name = scanner.nextLine();
      System.out.println("Phone number: ");

      String phoneNumber = scanner.nextLine();
      System.out.println("Address:\n");

      Address address = new Address();
      address.setFields(scanner);

      Client client = new Client(nextClientId++ , name, phoneNumber, address);
      this.clients.add(client);
   }
}
