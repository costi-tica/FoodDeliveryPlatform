package model.users;

import model.Review;

import java.util.List;

public class Courier extends User{
    private List<Review> reviews;

    public Courier(int id, String name, String phoneNumber, List<Review> reviews) {
        super(id, name, phoneNumber);
        this.reviews = reviews;
    }

    public List<Review> getReviews() {
        return reviews;
    }
}
