package main.application;

import model.*;
import model.products.Product;
import model.users.*;
import service.*;

import java.util.*;

public class AppManagement {
    AppData appData;
    RestaurantService restaurantService;
    UserService userService;
    OrderService orderService;
    ReviewService reviewService;
    ProductService productService;
    AddressService addressService;
    Scanner scanner = new Scanner(System.in);

    public AppManagement(AppData appData) {
        this.appData = appData;
        this.restaurantService = new RestaurantService();
        this.userService = new UserService();
        this.orderService = new OrderService();
        this.reviewService = new ReviewService();
        this.productService = new ProductService();
        this.addressService = new AddressService();
    }

    // GET
    public Restaurant getRestaurantByName(String name){
        return appData.restaurants.stream()
                .filter(res -> res.getName().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }
    public Client getClientByName(String name){
        return (Client) appData.users.stream()
                .filter(user -> user.getRole() == User.Role.CLIENT && user.getName().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }
    public Courier getCourierByName(String name){
        return (Courier) appData.users.stream()
                .filter(user -> user.getRole() == User.Role.COURIER && user.getName().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }

    // SHOW
    public void showUsers(){
        appData.users.forEach(user -> {
            System.out.println(user);
            System.out.println("_____________________");
        });
    }
    public void showClients(){
        appData.users.stream()
                .filter(user -> user.getRole() == User.Role.CLIENT)
                .forEach(client -> {
                    System.out.println(client);
                    System.out.println("_____________________");
                });
    }
    public void showCouriers(){
        appData.users.stream()
                .filter(user -> user.getRole() == User.Role.COURIER)
                .forEach(courier -> {
                    System.out.println(courier);
                    System.out.println("_____________________");
                });
    }
    public void showRestaurants(){
        appData.restaurants.forEach(res -> {
            System.out.println(res);
            System.out.println("___________________");
        });
    }
    public void showOrders(){
        appData.orders.forEach(order -> {
            System.out.println(order);
            System.out.println("_____________________");
        });
    }


    //SEARCH
    public void searchRestaurant(User userLoggedIn){
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
                case 2 -> placeOrder(res, userLoggedIn);
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

        Address address = addressService.createNewAddress();

        Client client = new Client.Builder()
                .withId(appData.getNextUserId())
                .withName(clientData.get("name"))
                .withPhoneNumber(clientData.get("phone number"))
                .withEmail(clientData.get("email"))
                .withPassword(clientData.get("password"))
                .withAddress(address)
                .withRole(User.Role.CLIENT)
                .build();

        appData.addUser(client);
    }
    // ADD COURIER
    public void addCourier(){
        Map<String, String> courierData = userService.getUserScannerData();

        Courier courier = new Courier.Builder()
                .withId(appData.getNextUserId())
                .withName(courierData.get("name"))
                .withPhoneNumber(courierData.get("phone number"))
                .withEmail(courierData.get("email"))
                .withPassword(courierData.get("password"))
                .withRole(User.Role.COURIER)
                .build();

        appData.addUser(courier);
    }
    // ADD RESTAURANT OWNER
    public void addResOwner(){
        Map<String, String> ownerData = userService.getUserScannerData();

        ResOwner owner = new ResOwner.Builder()
                .withId(appData.getNextUserId())
                .withName(ownerData.get("name"))
                .withPhoneNumber(ownerData.get("phone number"))
                .withEmail(ownerData.get("email"))
                .withPassword(ownerData.get("password"))
                .withRole(User.Role.RES_OWNER)
                .build();

        appData.addUser(owner);
    }
    // ADD RESTAURANT
    public void addRestaurant(User userLoggedIn){
        if (((ResOwner) userLoggedIn).getOwnedRestaurant() != null){
            System.out.println("You already have a restaurant.\n");
            return;
        }

        String[] restaurantData = restaurantService.getRestaurantScannerData().split("/");
        String name = restaurantData[0];
        Address address = addressService.createNewAddress();

        Restaurant res = new Restaurant.Builder()
                .withId(appData.getNextRestaurantId())
                .withName(name)
                .withEmptyMenu()
                .withNoReviews()
                .withAddress(address)
                .build();

        ((ResOwner) userLoggedIn).setOwnedRestaurant(res);
        appData.addRestaurant(res);
    }
    // ADD ORDER
    public void placeOrder(Restaurant res, User userLoggedIn){
        System.out.println("Product Ids: (ex: '3/6/7')");
        List<Product> prods = new ArrayList<>();
        for (String id : scanner.nextLine().split("/")){
            Product prod = productService.getProductById(res, Integer.parseInt(id));
            if (prod != null) prods.add(prod);
        }

        Order order = new Order(appData.getNextOrderId(), (Client) userLoggedIn, res, prods);
        orderService.calcTotalPrice(order);
        appData.addOrder(order);
    }
    public void placeOrder(User userLoggedIn) {
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

        Order order = new Order(appData.getNextOrderId(), (Client) userLoggedIn, resFound, prods);
        orderService.calcTotalPrice(order);
        appData.addOrder(order);
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
    public void editUserAddress(User user){
        addressService.editClientAddress((Client) user);
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

    // DELETE
    public void deleteUser() {
        System.out.println("User Id:");
        User user = userService.getUserById(appData.users, scanner.nextInt());
        scanner.nextLine();
        if (user != null) appData.users.remove(user);
    }
    public void deleteRestaurant() {
        System.out.println("Restaurant Id:");
        Restaurant res = restaurantService.getRestaurantById(appData.restaurants, scanner.nextInt());
        scanner.nextLine();
        if (res != null) appData.restaurants.remove(res);
    }
}
