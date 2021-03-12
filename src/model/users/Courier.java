package model.users;

import model.Review;

import java.util.ArrayList;
import java.util.List;

public class Courier extends User{
    private List<Review> reviews;

    public Courier(int id){
        super(id);
    }
    public Courier(int id, String name, String phoneNumber) {
        super(id, name, phoneNumber);
        this.reviews = new ArrayList<Review>();
    }

    public List<Review> getReviews() {
        return reviews;
    }
}
