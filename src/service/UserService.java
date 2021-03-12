package service;

import model.Address;
import model.Review;
import model.users.Client;
import model.users.Courier;
import model.users.User;

import java.util.List;
import java.util.Scanner;

public class UserService {
    private final Scanner scanner;

    public UserService(){
        scanner = new Scanner(System.in);
    }

//  ADD

//  ADD REVIEW FOR COURIER
    public void addReview(Courier courier, Client client){
        Review review = new Review();
        review.setFields(scanner);
        review.setUserId(client.getId());

        courier.getReviews().add(review);
    }
//  ADD ADDRESS FOR CLIENT
    public void addAddress(Client client, Address address){
        client.setAddress(address);
    }
    public void addAddress(Client client){
        Address address = new Address();
        address.setFields(scanner);

        client.setAddress(address);
    }

//  EDIT

//  EDIT REVIEW (COURIER)
    public void editReview(Courier courier, Client client){
        List<Review> reviews = courier.getReviews();

        for (Review r : reviews){
            if (r.getUserId() == client.getId()){
                System.out.println("Current review: " + r.toString() + '\n');
                r.updateFields(scanner);
                break;
            }
        }
    }
//  EDIT ADDRESS (CLIENT)
    public void editAddress(Client client){
        Address address = client.getAddress();
        System.out.println("Adresa actuala: \n" + address.toString() + '\n');
        System.out.println("Ce campuri doriti sa modificati? (cu / intre)");

        address.updateFields(scanner, scanner.nextLine().split("/"));
    }
//  EDIT NAME (CLIENT AND COURIER)
    public void editName(User user, String name){
        user.setName(name);
    }
    public void editName(User user) {
        System.out.println("New name: ");
        user.setName(scanner.nextLine());
    }
//  EDIT PHONE NUMBER (CLIENT AND COURIER)
    public void editPhoneNumber(User user, String phoneNumber){
        user.setPhoneNumber(phoneNumber);
    }
    public void editPhoneNumber(User user){
        System.out.println("New phone number: ");
        user.setPhoneNumber(scanner.nextLine());
    }

//  DELETE

//  DELETE REVIEW (COURIER)
    public void deleteReview(Courier courier, Client client){
        List<Review> reviews = courier.getReviews();
        Review toDelete = null;
        for (Review r : reviews){
            if (r.getUserId() == client.getId()){
                toDelete = r;
                break;
            }
        }
        if (toDelete != null) {
            reviews.remove(toDelete);
        }
    }
}
