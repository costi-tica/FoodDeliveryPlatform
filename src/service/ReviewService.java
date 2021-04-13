package service;

import model.Restaurant;
import model.Review;
import model.users.Client;

import java.util.Scanner;

public final class ReviewService {
    private final Scanner scanner;

    public ReviewService() {
        scanner = new Scanner(System.in);
    }

//  GET RESTAURANT REVIEW BY CLIENT
    public Review getReviewByClient(Restaurant res, Client client){
        return res.getReviews()
                .stream()
                .filter(review -> review.getClient().getId() == client.getId())
                .findFirst().orElse(null);
    }

//  SHOW REVIEWS
    public void showReviews(Restaurant res) {
        res.getReviews().forEach(review -> {
            System.out.println(review);
            System.out.println("________________");
        });
    }

//  SET FIELDS (SCANNER)
    public String getReviewScannerData(){
        System.out.println("number of stars/message");
        return scanner.nextLine();
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
        String[] reviewData = getReviewScannerData().split("/");
        int numOfStars = Integer.parseInt(reviewData[0]);
        String message = reviewData[1];

        Review review = new Review.Builder()
                .withNumOfStars(numOfStars)
                .withMessage(message)
                .withClient(client)
                .withCurrentDate()
                .build();

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
