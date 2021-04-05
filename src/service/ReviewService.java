package service;

import model.Restaurant;
import model.Review;
import model.users.Client;

import java.util.List;
import java.util.Scanner;

public class ReviewService {
    private final Scanner scanner;

    public ReviewService() {
        scanner = new Scanner(System.in);
    }

//  GET RESTAURANT REVIEW BY CLIENT
    public Review getReviewByClient(Restaurant res, Client client){
        for (Review r : res.getReviews()){
            if (r.getClient().getId() == client.getId()){
                return r;
            }
        }
        return null;
    }

//  SHOW REVIEWS
    public void showReviews(Restaurant res) {
        for (Review r : res.getReviews()){
            System.out.println(r);
            System.out.println("________________");
        }
    }

//  SET FIELDS (SCANNER)
    public void setFields(Review review){
        System.out.println("Number of stars: ");
        review.setNumOfStars(scanner.nextInt());
        scanner.nextLine();
        System.out.println("Message: ");
        review.setMessage(scanner.nextLine());
    }
//  UPDATE FIELDS (SCANNER)
    public void updateFields(Review review){
        System.out.println("New number of stars: ");
        review.setNumOfStars(scanner.nextInt());
        scanner.nextLine();
        System.out.println("New message: ");
        review.setMessage(scanner.nextLine());
    }

//  ADD REVIEW TO RESTAURANT
    public void addReview(Restaurant res, Review review){
        res.getReviews().add(review);
    }
    public void addReview(Restaurant res, Client client){
        Review review = new Review();
        setFields(review);
        review.setClient(client);

        addReview(res, review);
    }

//  EDIT RESTAURANT REVIEW
    public void editReview(Restaurant res, Client client){
        Review review = getReviewByClient(res, client);
        if (review == null) return;

        System.out.println("Your review: \n" + review.toString() + '\n');
        updateFields(review);
    }

//  DELETE RESTAURANT REVIEW
    public void deleteReview(Restaurant res, Client client){
        Review toDelete = getReviewByClient(res, client);
        if (toDelete != null) res.getReviews().remove(toDelete);
    }
}
