package main;

import model.Address;
import model.Order;
import model.Restaurant;
import model.products.Product;
import model.users.Client;
import model.users.Courier;
import service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public final class Application {
   private final List<Client> clients;
   private final List<Courier> couriers;
   private final List<Restaurant> restaurants;
   private final List<Order> orders;
   private int nextClientId, nextCourierId, nextRestaurantId, nextOrderId;
   RestaurantService restaurantService;
   UserService userService;
   OrderService orderService;
   ReviewService reviewService;
   ProductService productService;
   AddressService addressService;

   public Application(){
      this.clients = new ArrayList<>();
      this.couriers = new ArrayList<>();
      this.restaurants = new ArrayList<>();
      this.orders = new ArrayList<>();
      this.restaurantService = new RestaurantService();
      this.userService = new UserService();
      this.orderService = new OrderService();
      this.reviewService = new ReviewService();
      this.productService = new ProductService();
      this.addressService = new AddressService();
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

// SEARCH
   public void searchFor(Object object){
      Scanner scanner = new Scanner(System.in);
      int option;
      String objName = "";
      System.out.println("Name:");
      String name = scanner.nextLine();

      if (object instanceof Client) {
         object = getClientByName(name);
         objName = "Client";
      } else if (object instanceof Courier){
         object = getCourierByName(name);
         objName = "Courier";
      } else if (object instanceof Restaurant){
         object = getRestaurantByName(name);
         objName = "Restaurant";
      }

      if (object == null){
         System.out.println(objName + " does not exist.\n");
         return;
      }

      System.out.println(object);
      System.out.printf("""
              1) Select %s for further actions
              2) Go back to the main menu
              OPTION:%n""", objName);
      option = scanner.nextInt();
      scanner.nextLine();

      if (option == 2) return;

      if (objName.equalsIgnoreCase("Client"))
         editClient((Client) object);
      else if (objName.equalsIgnoreCase("Courier"))
         editCourier((Courier) object);
      else if (objName.equalsIgnoreCase("Restaurant"))
         editRestaurant((Restaurant) object);
   }

// ADD

// ADD CLIENT
   public void addClient(){
      Client client = new Client(nextClientId++);

      userService.setClientFields(client);
      addressService.addClientAddress(client);
      this.clients.add(client);
   }
// ADD COURIER
   public void addCourier(){
      Courier courier = new Courier(nextCourierId++);

      userService.setCourierFields(courier);
      this.couriers.add(courier);
   }
// ADD RESTAURANT
   public void addRestaurant(){
      Restaurant res = new Restaurant(nextRestaurantId++);

      restaurantService.setFields(res);
      addressService.addRestaurantAddress(res);
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
         Product prod = productService.getProductById(resFound, Integer.parseInt(id));
         if (prod != null) prods.add(prod);
      }

      Order order = new Order(nextOrderId++, clientFound, resFound, courierFound, prods);
      orderService.calcTotalPrice(order);
      orderService.calcEstimatedTime(order);
      this.orders.add(order);
   }

// EDIT

// EDIT CLIENT
   public void editClient(Client client){
      Scanner scanner = new Scanner(System.in);
      int option;
      while (true){
         System.out.println("""
                 1) Edit name
                 2) Edit phone number
                 3) Edit address
                 4) Show info
                 5) Go back to the main menu
                 OPTION:""");
         option = scanner.nextInt();
         scanner.nextLine();
         switch (option){
            case 1 -> userService.editName(client);
            case 2 -> userService.editPhoneNumber(client);
            case 3 -> addressService.editClientAddress(client);
            case 4 -> System.out.println(client);
            case 5 -> {
               return;
            }
         }
      }
   }
// EDIT COURIER
   public void editCourier(Courier courier){
      Scanner scanner = new Scanner(System.in);
      int option;
      while (true){
         System.out.println("""
                 1) Edit name
                 2) Edit phone number
                 3) Show info
                 4) Go back to the main menu
                 OPTION:""");
         option = scanner.nextInt();
         scanner.nextLine();
         switch (option){
            case 1 -> userService.editName(courier);
            case 2 -> userService.editPhoneNumber(courier);
            case 3 -> System.out.println(courier);
            case 4 -> {
               return;
            }
         }
      }
   }
// EDIT RESTAURANT
   public void editRestaurant(Restaurant res){
      Scanner scanner = new Scanner(System.in);
      int option;
      while (true){
         System.out.println("""
                 1) Add dish
                 2) Add drink
                 3) Add dish category
                 4) Add drink category
                 5) Edit name
                 6) Edit address
                 7) Edit product
                 8) Delete product
                 9) Delete dish category
                 10) Delete drink category
                 11) Show reviews
                 12) Edit review
                 13) Delete review
                 14) Show menu
                 15) Add review
                 16) Show info
                 17) Go back
                 OPTION:""");
         option = scanner.nextInt();
         scanner.nextLine();
         switch (option) {
            case 1 -> restaurantService.addDish(res);
            case 2 -> restaurantService.addDrink(res);
            case 3 -> restaurantService.addDishCategory(res);
            case 4 -> restaurantService.addDrinkCategory(res);
            case 5 -> restaurantService.editName(res);
            case 6 -> addressService.editRestaurantAddress(res);
            case 7 -> {
               System.out.println("Product id:");
               int id = scanner.nextInt();
               scanner.nextLine();
               productService.editProduct(res, id);
            }
            case 8 -> {
               System.out.println("Product id:");
               int id = scanner.nextInt();
               scanner.nextLine();
               productService.deleteProduct(res, id);
            }
            case 9 -> {
               System.out.println("Category name:");
               String name = scanner.nextLine();
               restaurantService.deleteDishCategory(res, name);
            }
            case 10 -> {
               System.out.println("Category name:");
               restaurantService.deleteDrinkCategory(res, scanner.nextLine());
            }
            case 11 -> reviewService.showReviews(res);
            case 12 -> {
               System.out.println("Client name:");
               Client client = getClientByName(scanner.nextLine());
               if (client == null) System.out.println("Client does not exist");
               else                reviewService.editReview(res, client);
            }
            case 13 -> {
               System.out.println("Client name:");
               Client client = getClientByName(scanner.nextLine());
               if (client == null) System.out.println("Client does not exist");
               else                reviewService.deleteReview(res, client);
            }
            case 14 -> restaurantService.showMenu(res);
            case 15 -> {
               System.out.println("Client name:");
               Client client = getClientByName(scanner.nextLine());
               if (client == null) System.out.println("Client does not exist");
               else                reviewService.addReview(res, client);
            }
            case 16 -> System.out.println(res);
            case 17 -> {
               return;
            }
         }
      }
   }
}
