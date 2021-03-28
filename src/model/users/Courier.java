package model.users;

import model.Review;

import java.util.ArrayList;
import java.util.List;

public class Courier extends User{
    private List<Review> reviews;

    public Courier(){}
    public Courier(int id){
        super(id);
        this.reviews = new ArrayList<Review>();
    }
    public Courier(int id, String name, String phoneNumber) {
        super(id, name, phoneNumber);
        this.reviews = new ArrayList<Review>();
    }

    @Override
    public String toString(){
        double rating = getRating();
        String ratingMessage = rating == 0 ? "No reviews" : Double.toString(rating);
        return super.toString() + '\n' +
                "Rating: " + ratingMessage;

    }

    public double getRating(){
        int s = 0;
        for (Review r : reviews){
            s += r.getNumOfStars();
        }
        return reviews.size() > 0 ? s * 1.0 / reviews.size() : 0;
    }

    public List<Review> getReviews() {
        return reviews;
    }
}
