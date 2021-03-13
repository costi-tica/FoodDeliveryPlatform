package model;

import model.users.Client;

import java.util.Date;
import java.util.Scanner;

public class Review {
    private int numOfStars;
    private String message;
    private Client client;
    private final Date date;

    public Review(){
        this.date = new Date();
    }
    public Review(int numOfStars, String message, Client client) {
        this.numOfStars = numOfStars;
        this.message = message;
        this.client = client;
        this.date = new Date();
    }

    @Override
    public String toString(){
        return "Stars: " + numOfStars + '\n' +
                "Message: " + message + '\n' +
                "Client: " + client.getName();
    }

    public void setFields(Scanner scanner){
        System.out.println("Number of stars: ");
        this.numOfStars = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Message: ");
        this.message = scanner.nextLine();
    }

    public void updateFields(Scanner scanner){
        System.out.println("New number of stars: ");
        this.numOfStars = scanner.nextInt();
        scanner.nextLine();
        System.out.println("New message: ");
        this.message = scanner.nextLine();
    }

    public int getNumOfStars() {
        return numOfStars;
    }

    public void setNumOfStars(int numOfStars) {
        this.numOfStars = numOfStars;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
