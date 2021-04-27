package model;

import model.users.Client;

import java.sql.Timestamp;
import java.util.Date;

public final class Review {
    private int numOfStars;
    private String message;
    private Client client;
    private Date date;

    public Review(){
        this.date = new Date();
    }

    public static class Builder{
        private final Review review = new Review();

        public Builder withNumOfStars(int numOfStars){
            review.setNumOfStars(numOfStars);
            return this;
        }
        public Builder withMessage(String message){
            review.setMessage(message);
            return this;
        }
        public Builder withClient(Client client){
            review.setClient(client);
            return this;
        }
        public Builder withCurrentDate(){
            review.setDate(new Date());
            return this;
        }
        public Builder withDate(Date date){
            review.setDate(date);
            return this;
        }
        public Review build(){
            return review;
        }
    }

    @Override
    public String toString(){
        return "Stars: " + numOfStars + '\n' +
                "Message: " + message + '\n' +
                "Client: " + client.getName() + '\n' +
                "Date: " + new Timestamp(date.getTime());
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() { return date; }

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
