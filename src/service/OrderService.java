package service;

import model.Order;
import model.products.Product;

public class OrderService {
    public OrderService() {}

//  CALC TOTAL PRICE
    public void calcTotalPrice(Order order){
        double sum = 0;
        for (Product prod : order.getProducts()){
            sum += prod.getPrice();
        }
        order.setTotalPrice(sum);
    }

//  CALC ESTIMATED DELIVERY TIME
    public void calcEstimatedTime(Order order){
        order.setEstimatedTime(0); // for now
    }
}
