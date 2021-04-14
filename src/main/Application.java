package main;

import config.adminConfig;
import model.Address;
import model.Order;
import model.Restaurant;
import model.Review;
import model.products.Product;
import model.users.Client;
import model.users.Courier;
import model.users.ResOwner;
import model.users.User;
import service.*;

import java.util.*;


public final class Application {
   private final List<User> users;
   private final List<Restaurant> restaurants;
   private final List<Order> orders;
   RestaurantService restaurantService;
   UserService userService;
   OrderService orderService;
   ReviewService reviewService;
   ProductService productService;
   AddressService addressService;

   private User userLoggedIn;

   private final Scanner scanner = new Scanner(System.in);

   private static int nextUserId, nextRestaurantId, nextOrderId;

   public Application(){
      this.users = new ArrayList<>();
      this.restaurants = new ArrayList<>();
      this.orders = new ArrayList<>();
      this.restaurantService = new RestaurantService();
      this.userService = new UserService();
      this.orderService = new OrderService();
      this.reviewService = new ReviewService();
      this.productService = new ProductService();
      this.addressService = new AddressService();

      appConfig();
   }

// APP CONFIG
   private void appConfig(){
      adminConfig.config(users);
      // etc
   }

// REGISTER
   public void register(){
      int option;
      System.out.println("""
              Register as:
              1) Client
              2) Courier
              3) Restaurant Owner
              4) Exit
              OPTION:""");
      option = scanner.nextInt();
      scanner.nextLine();
      switch (option){
         case 1 -> addClient();
         case 2 -> addCourier();
         case 3 -> addResOwner();
         case 4 -> System.exit(0);
      }
   }

// LOGIN
   public void login(){
      System.out.println("LOGIN\nEmail:");
      String email = scanner.nextLine();
      System.out.println("Password:");
      String password = scanner.nextLine();

      User user = users.stream()
              .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
              .findFirst().orElse(null);

      if (user != null){
         user.setLoggedIn(true);
         setLogoutTimer(user, 2, 0);
         userLoggedIn = user;
         return;
      }

      System.out.println("""
              Email or password is incorrect!
              1) Try again
              2) Main menu
              3) Exit
              OPTION:""");
      int option = scanner.nextInt();
      scanner.nextLine();
      switch (option){
         case 1 -> login();
         case 2 -> menu();
         case 3 -> System.exit(0);
      }
   }
//  LOGOUT
   public void logout(User user){
      user.setLoggedIn(false);
      userLoggedIn = null;
   }
   public void setLogoutTimer(User user, int hours, int minutes){
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(new Date());
      calendar.add(Calendar.HOUR_OF_DAY, hours);
      calendar.add(Calendar.MINUTE, minutes);
      Timer timer = new Timer();
      timer.schedule(new TimerTask() {
         public void run() {
            System.out.println("Session expired!\n");
            logout(user);
         }
      }, calendar.getTime());
   }

// MENU
   public void menu(){
      int option;
      System.out.println("""
              1) Register
              2) Login
              3) Exit
              OPTION:""");
      option = scanner.nextInt();
      scanner.nextLine();
      switch (option){
         case 1 -> {
            register();
            login();
         }
         case 2 -> login();
         case 3 -> System.exit(0);
      }

      switch (userLoggedIn.getRole()){
         case CLIENT -> clientMenu();
         case COURIER -> courierMenu();
         case RES_OWNER -> resOwnerMenu();
         case APP_ADMIN -> appAdminMenu();
      }
   }

   public void clientMenu(){
      int option;
      while (true){
         System.out.println("""
                Choose:\s
                1) Place order
                2) View all restaurants
                3) Search for a restaurant
                4) View your orders
                5) Edit your account
                6) Edit your address
                7) Logout
                8) Exit
                OPTION:""");

         option = scanner.nextInt();
         scanner.nextLine();
         if (userLoggedIn == null) menu();

         switch (option) {
            case 1 -> placeOrder();
            case 2 -> showRestaurants();
            case 3 -> searchRestaurant();
            case 4 -> orders.stream()
                       .filter(ord -> ord.getClient().getId() == userLoggedIn.getId())
                       .forEach(ord -> {
                          System.out.println(ord);
                          System.out.println("___________________");
                       });
            case 5 -> editUserAccount(userLoggedIn);
            case 6 -> addressService.editClientAddress((Client) userLoggedIn);
            case 7 -> {
               logout(userLoggedIn);
               menu();
            }
            case 8 -> System.exit(0);
         }
      }
   }
   public void courierMenu(){
      int option;
      while (true){
         System.out.println("""
                Choose:\s
                1) Accept order
                2) Mark active order as delivered
                3) View available orders
                4) Edit your account
                5) Logout
                6) Exit
                OPTION:""");

         option = scanner.nextInt();
         scanner.nextLine();
         if (userLoggedIn == null) menu();

         switch (option) {
            case 1 -> {
               if (((Courier) userLoggedIn).isBusy()){
                  System.out.println("You already have an active order.");
                  break;
               }
               System.out.println("Order Id:");
               int orderId = scanner.nextInt();
               scanner.nextLine();
               Order order = orders.stream()
                       .filter(ord -> ord.getId() == orderId)
                       .findFirst()
                       .orElse(null);
               if (order == null){
                  System.out.println("Order does not exist or it is no longer available\n");
                  break;
               }
               order.setCourier((Courier)userLoggedIn);
               order.setStatus(Order.Status.SHIPPING);
               ((Courier) userLoggedIn).setBusy(true);
            }
            case 2 -> {
               Order order = orders.stream()
                       .filter(ord -> ord.getCourier() != null && ord.getCourier().getId() == userLoggedIn.getId() && ord.getStatus() == Order.Status.SHIPPING)
                       .findFirst()
                       .orElse(null);
               if (order == null){
                  System.out.println("You do not have any active orders\n");
                  break;
               }
               System.out.println("Active order:\n" + order.toString());
               System.out.println("Do you want to mark it as delivered? yes/no");
               if (scanner.nextLine().equalsIgnoreCase("yes")){
                  order.setStatus(Order.Status.COMPLETED);
                  ((Courier) userLoggedIn).setBusy(false);
               }
            }
            case 3 -> orders.stream()
                       .filter(ord -> ord.getStatus() == Order.Status.PLACED)
                       .forEach(ord -> {
                          System.out.println(ord);
                          System.out.println("_______________");
                       });
            case 4 -> editUserAccount(userLoggedIn);
            case 5 -> {
               logout(userLoggedIn);
               menu();
            }
            case 6 -> System.exit(0);
         }
      }
   }
   public void resOwnerMenu(){
      int option;
      while (true) {
         System.out.println("""
                 Choose:\s
                 1) Add your restaurant
                 2) Edit your restaurant
                 3) Edit your account
                 4) Logout
                 5) Exit
                 OPTION:""");

         option = scanner.nextInt();
         scanner.nextLine();
         if (userLoggedIn == null) menu();

         switch (option) {
            case 1 -> addRestaurant();
            case 2 -> {
               if (((ResOwner) userLoggedIn).getOwnedRestaurant() == null){
                  System.out.println("You do not have a restaurant yet.\n");
                  break;
               }
               editRestaurant(((ResOwner) userLoggedIn).getOwnedRestaurant());
            }
            case 3 -> editUserAccount(userLoggedIn);
            case 4 -> {
               logout(userLoggedIn);
               menu();
            }
            case 5 -> System.exit(0);
         }
      }
   }
   public void appAdminMenu(){
      int option;
      while (true) {
         System.out.println("""
                 Choose:\s
                 1) View all users
                 2) View all restaurants
                 3) Delete user
                 4) Delete restaurant
                 5) Search for a user
                 6) Search for a restaurant
                 7) Logout
                 8) Exit
                 OPTION:""");

         option = scanner.nextInt();
         scanner.nextLine();
         if (userLoggedIn == null) menu();

         switch (option){
            case 1 -> showUsers();
            case 2 -> showRestaurants();
            // ...to do
            case 7 -> {
               logout(userLoggedIn);
               menu();
            }
            case 8 -> System.exit(0);
         }
      }
   }

// GET
   public Restaurant getRestaurantByName(String name){
      return restaurants.stream()
              .filter(res -> res.getName().equalsIgnoreCase(name))
              .findFirst().orElse(null);
   }
   public Client getClientByName(String name){
      return (Client)users.stream()
              .filter(user -> user.getRole() == User.Role.CLIENT && user.getName().equalsIgnoreCase(name))
              .findFirst().orElse(null);
   }
   public Courier getCourierByName(String name){
      return (Courier)users.stream()
              .filter(user -> user.getRole() == User.Role.COURIER && user.getName().equalsIgnoreCase(name))
              .findFirst().orElse(null);
   }

// SHOW
   public void showUsers(){
      users.forEach(user -> {
         System.out.println(user);
         System.out.println("_____________________");
      });
   }
   public void showClients(){
      users.stream()
              .filter(user -> user.getRole() == User.Role.CLIENT)
              .forEach(client -> {
                  System.out.println(client);
                  System.out.println("_____________________");
              });
   }
   public void showCouriers(){
      users.stream()
              .filter(user -> user.getRole() == User.Role.COURIER)
              .forEach(courier -> {
                  System.out.println(courier);
                  System.out.println("_____________________");
              });
   }
   public void showRestaurants(){
      restaurants.forEach(res -> {
         System.out.println(res);
         System.out.println("___________________");
      });
   }
   public void showOrders(){
      orders.forEach(order -> {
         System.out.println(order);
         System.out.println("_____________________");
      });
   }

//SEARCH
   public void searchRestaurant(){
      System.out.println("Restaurant name:");
      Restaurant res = getRestaurantByName(scanner.nextLine());
      if (res == null){
         System.out.println("Restaurant does not exist.\n");
         return;
      }
      System.out.println(res);

      int option;
      while (true){
         System.out.println("""
                 1) Show menu
                 2) Place order
                 3) Add review
                 4) Edit your review
                 5) Delete your review
                 6) Show info
                 7) Go back
                 8) Exit
                 OPTION:""");
         option = scanner.nextInt();
         scanner.nextLine();

         switch (option) {
            case 1 -> restaurantService.showMenu(res);
            case 2 -> placeOrder(res);
            case 3 -> {
               Review review = reviewService.getReviewByClient(res, (Client) userLoggedIn);
               if (review != null){
                  System.out.println("You already have a review for this restaurant.");
                  break;
               }

               reviewService.addReview(res, (Client) userLoggedIn);
            }
            case 4 -> {
               Review review = reviewService.getReviewByClient(res, (Client) userLoggedIn);
               if (review == null){
                  System.out.println("You do not have a review for this restaurant.");
                  break;
               }
               reviewService.editReview(res, (Client) userLoggedIn);
            }
            case 5 -> reviewService.deleteReview(res, (Client) userLoggedIn);
            case 6 -> System.out.println(res);
            case 7 -> { return; }
            case 8 -> System.exit(0);
         }
      }
   }

// ADD CLIENT
   public void addClient(){
      Map<String, String> clientData = userService.getUserScannerData();

      Address address = addressService.createNewAddress(AddressService.CreationPath.USER_INPUT);

      Client client = new Client.Builder()
              .withId(getNextUserId())
              .withName(clientData.get("name"))
              .withPhoneNumber(clientData.get("phone number"))
              .withEmail(clientData.get("email"))
              .withPassword(clientData.get("password"))
              .withAddress(address)
              .withRole(User.Role.CLIENT)
              .build();

      this.users.add(client);
   }
// ADD COURIER
   public void addCourier(){
      Map<String, String> courierData = userService.getUserScannerData();

      Courier courier = new Courier.Builder()
              .withId(getNextUserId())
              .withName(courierData.get("name"))
              .withPhoneNumber(courierData.get("phone number"))
              .withEmail(courierData.get("email"))
              .withPassword(courierData.get("password"))
              .withRole(User.Role.COURIER)
              .build();

      this.users.add(courier);
   }
// ADD RESTAURANT OWNER
   public void addResOwner(){
      Map<String, String> ownerData = userService.getUserScannerData();

      ResOwner owner = new ResOwner.Builder()
              .withId(getNextUserId())
              .withName(ownerData.get("name"))
              .withPhoneNumber(ownerData.get("phone number"))
              .withEmail(ownerData.get("email"))
              .withPassword(ownerData.get("password"))
              .withRole(User.Role.RES_OWNER)
              .build();

      this.users.add(owner);
   }
// ADD RESTAURANT
   public void addRestaurant(){
      if (((ResOwner) userLoggedIn).getOwnedRestaurant() != null){
         System.out.println("You already have a restaurant.\n");
         return;
      }

      String[] restaurantData = restaurantService.getRestaurantScannerData().split("/");
      String name = restaurantData[0];
      Address address = addressService.createNewAddress(AddressService.CreationPath.USER_INPUT);

      Restaurant res = new Restaurant.Builder()
              .withId(getNextRestaurantId())
              .withName(name)
              .withEmptyMenu()
              .withNoReviews()
              .withAddress(address)
              .build();

      ((ResOwner) userLoggedIn).setOwnedRestaurant(res);
      this.restaurants.add(res);
   }
// ADD ORDER
   public void placeOrder(Restaurant res){
      System.out.println("Product Ids: (ex: '3/6/7')");
      List<Product> prods = new ArrayList<>();
      for (String id : scanner.nextLine().split("/")){
         Product prod = productService.getProductById(res, Integer.parseInt(id));
         if (prod != null) prods.add(prod);
      }

      Order order = new Order(getNextOrderId(), (Client) userLoggedIn, res, prods);
      orderService.calcTotalPrice(order);
      this.orders.add(order);
   }
   public void placeOrder() {
      int option;
      Restaurant resFound = null;
      option = 1;
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
            System.out.println("""
                    1) Type the name again
                    2) Show all restaurants
                    3) Exit
                    OPTION:""");
            option = scanner.nextInt();
            scanner.nextLine();
         }
      }

      System.out.println("Product Ids: (ex: '3/6/7')");
      List<Product> prods = new ArrayList<>();
      for (String id : scanner.nextLine().split("/")){
         Product prod = productService.getProductById(resFound, Integer.parseInt(id));
         if (prod != null) prods.add(prod);
      }

      Order order = new Order(getNextOrderId(), (Client) userLoggedIn, resFound, prods);
      orderService.calcTotalPrice(order);
      this.orders.add(order);
   }

// EDIT USER ACCOUNT
   public void editUserAccount(User user){
      int option;
      while (true){
         System.out.println("""
                 1) Edit name
                 2) Edit phone number
                 3) Edit email
                 4) Edit password
                 5) View account info
                 6) Go back to the main menu
                 OPTION:""");
         option = scanner.nextInt();
         scanner.nextLine();
         switch (option){
            case 1 -> userService.editName(user);
            case 2 -> userService.editPhoneNumber(user);
            case 3 -> userService.editEmail(user);
            case 4 -> userService.editPassword(user);
            case 5 -> System.out.println(user);
            case 6 -> {
               return;
            }
         }
      }
   }
// EDIT RESTAURANT
   public void editRestaurant(Restaurant res){
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
                 12) Show menu
                 13) Show info
                 14) Go back
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
            case 12 -> restaurantService.showMenu(res);
            case 13 -> System.out.println(res);
            case 14 -> {
               return;
            }
         }
      }
   }

// GETTERS AND SETTERS
   public static int getNextUserId() {
      return nextUserId++;
   }
   private static int getNextOrderId() {
      return nextOrderId++;
   }
   private static int getNextRestaurantId() {
      return nextRestaurantId++;
   }
}
