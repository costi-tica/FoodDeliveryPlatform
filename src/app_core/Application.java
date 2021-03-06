package app_core;

import model.*;
import model.users.Courier;
import model.users.ResOwner;

import java.util.*;


public final class Application {
    AppData appData;
    AppManagement appManagement;
    AppConfig appConfig;
    private long userLoggedIn;
    private final Scanner scanner = new Scanner(System.in);
    private static Application INSTANCE;

    private Application(){
        this.appData = AppData.getInstance();
        this.appManagement = new AppManagement();
        this.appConfig = new AppConfig();
    }

    public static Application getInstance() {
        if (INSTANCE == null) INSTANCE = new Application();
        return INSTANCE;
    }

    // START
    public void start() {
        appConfig.config();
        //appManagement.readFileData();

        menu();
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
            case 1 -> appManagement.newClient();
            case 2 -> appManagement.newCourier();
            case 3 -> appManagement.newResOwner();
            case 4 -> System.exit(0);
            default -> System.out.println("Invalid command.");
        }
    }
    // LOGIN
//    public void login(){
//        System.out.println("LOGIN\nEmail:");
//        String email = scanner.nextLine();
//        System.out.println("Password:");
//        String password = scanner.nextLine();
//
//        long userId = appManagement.login(email, password);
//
//        if (userId != -1){
//            setLogoutTimer(2, 0);
//            userLoggedIn = userId;
//            return;
//        }
//
//        System.out.println("""
//              Email or password is incorrect!
//              1) Try again
//              2) Main menu
//              3) Exit
//              OPTION:""");
//        int option = scanner.nextInt();
//        scanner.nextLine();
//        switch (option){
//            case 1 -> login();
//            case 2 -> menu();
//            case 3 -> System.exit(0);
//        }
//    }
//    // LOGOUT
//    private void logout(){
//        userLoggedIn = -1;
//        menu();
//    }
//    private void setLogoutTimer(int hours, int minutes){
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        calendar.add(Calendar.HOUR_OF_DAY, hours);
//        calendar.add(Calendar.MINUTE, minutes);
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            public void run() {
//                System.out.println("Session expired!\n");
//                logout();
//            }
//        }, calendar.getTime());
//    }

    public void menu(){
        int option;
        System.out.println("""
              1) REGISTER
              2) LOGIN (nefunctional)
              3) EXIT
              OPTION:""");
        option = scanner.nextInt();
        scanner.nextLine();
        switch (option){
            case 1 -> {
                register();
                //login();
                menu();
            }
            //case 2 -> login();
            case 3 -> System.exit(0);
        }
//
//        switch (appManagement.getRole(userLoggedIn)){
//            case CLIENT -> clientMenu();
//            case COURIER -> courierMenu();
//            case RES_OWNER -> resOwnerMenu();
//            case APP_ADMIN -> appAdminMenu();
//        }
    }
//
//    public void clientMenu(){
//        int option;
//        while (true){
//            System.out.println("""
//                Choose:\s
//                1) Place order
//                2) View all restaurants
//                3) Select a restaurant
//                4) View your orders
//                5) Edit your account
//                6) Edit your address
//                7) Logout
//                8) Exit
//                OPTION:""");
//
//            option = scanner.nextInt();
//            scanner.nextLine();
//            if (userLoggedIn == -1) menu();
//
//            switch (option) {
//                case 1 -> appManagement.placeOrder(userLoggedIn);
//                case 2 -> appManagement.showRestaurants();
//                case 3 -> appManagement.selectRestaurant(userLoggedIn);
//                case 4 -> appData.orders.stream()
//                        .filter(ord -> ord.getClient().getId() == userLoggedIn.getId())
//                        .forEach(ord -> {
//                            System.out.println(ord);
//                            System.out.println("___________________");
//                        });
//                case 5 -> appManagement.editUserAccount(userLoggedIn);
//                case 6 -> appManagement.editUserAddress(userLoggedIn);
//                case 7 -> logout(userLoggedIn);
//                case 8 -> System.exit(0);
//            }
//        }
//    }
//    public void courierMenu(){
//        int option;
//        while (true){
//            System.out.println("""
//                Choose:\s
//                1) Accept order
//                2) Mark active order as delivered
//                3) View available orders
//                4) Edit your account
//                5) Logout
//                6) Exit
//                OPTION:""");
//
//            option = scanner.nextInt();
//            scanner.nextLine();
//            if (userLoggedIn == null) menu();
//
//            switch (option) {
//                case 1 -> {
//                    if (((Courier) userLoggedIn).isBusy()){
//                        System.out.println("You already have an active order.");
//                        break;
//                    }
//                    System.out.println("Order Id:");
//                    int orderId = scanner.nextInt();
//                    scanner.nextLine();
//                    Order order = appData.orders.stream()
//                            .filter(ord -> ord.getId() == orderId)
//                            .findFirst()
//                            .orElse(null);
//                    if (order == null){
//                        System.out.println("Order does not exist or it is no longer available\n");
//                        break;
//                    }
//                    order.setCourier((Courier)userLoggedIn);
//                    order.setStatus(Order.Status.ON_DELIVERY);
//                    ((Courier) userLoggedIn).setBusy(true);
//                }
//                case 2 -> {
//                    Order order = appData.orders.stream()
//                            .filter(ord -> ord.getCourier() != null && ord.getCourier().getId() == userLoggedIn.getId() && ord.getStatus() == Order.Status.ON_DELIVERY)
//                            .findFirst()
//                            .orElse(null);
//                    if (order == null){
//                        System.out.println("You do not have any active orders\n");
//                        break;
//                    }
//                    System.out.println("Active order:\n" + order.toString());
//                    System.out.println("Do you want to mark it as delivered? yes/no");
//                    if (scanner.nextLine().equalsIgnoreCase("yes")){
//                        order.setStatus(Order.Status.COMPLETED);
//                        ((Courier) userLoggedIn).setBusy(false);
//                    }
//                }
//                case 3 -> appData.orders.stream()
//                        .filter(ord -> ord.getStatus() == Order.Status.PLACED)
//                        .forEach(ord -> {
//                            System.out.println(ord);
//                            System.out.println("_______________");
//                        });
//                case 4 -> appManagement.editUserAccount(userLoggedIn);
//                case 5 -> logout(userLoggedIn);
//                case 6 -> System.exit(0);
//            }
//        }
//    }
//    public void resOwnerMenu(){
//        int option;
//        while (true) {
//            System.out.println("""
//                 Choose:\s
//                 1) Add your restaurant
//                 2) Edit your restaurant
//                 3) Edit your account
//                 4) Logout
//                 5) Exit
//                 OPTION:""");
//
//            option = scanner.nextInt();
//            scanner.nextLine();
//            if (userLoggedIn == null) menu();
//
//            switch (option) {
//                case 1 -> appManagement.newRestaurant(userLoggedIn);
//                case 2 -> {
//                    if (((ResOwner) userLoggedIn).getOwnedRestaurant() == null){
//                        System.out.println("You do not have a restaurant yet.\n");
//                        break;
//                    }
//                    appManagement.editRestaurant(((ResOwner) userLoggedIn).getOwnedRestaurant());
//                }
//                case 3 -> appManagement.editUserAccount(userLoggedIn);
//                case 4 -> logout(userLoggedIn);
//                case 5 -> System.exit(0);
//            }
//        }
//    }
//    public void appAdminMenu(){
//        int option;
//        while (true) {
//            System.out.println("""
//                 Choose:\s
//                 1) View all users
//                 2) View all restaurants
//                 3) Delete user
//                 4) Delete restaurant
//                 5) Logout
//                 6) Exit
//                 OPTION:""");
//
//            option = scanner.nextInt();
//            scanner.nextLine();
//            if (userLoggedIn == null) menu();
//
//            switch (option){
//                case 1 -> appManagement.showUsers();
//                case 2 -> appManagement.showRestaurants();
//                case 3 -> appManagement.deleteUser();
//                case 4 -> appManagement.deleteRestaurant();
//                case 5 -> logout(userLoggedIn);
//                case 6 -> System.exit(0);
//            }
//        }
//    }
}
