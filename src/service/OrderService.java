package service;

import model.Order;

public final class OrderService {
    public OrderService() {}

//  CALC TOTAL PRICE
    public void calcTotalPrice(Order order){
        double totalPrice = order.getProducts()
                .stream()
                .mapToDouble(prod -> prod.getPrice())
                .sum();
        order.setTotalPrice(totalPrice);
    }

//  CALC ESTIMATED DELIVERY TIME
    public void calcEstimatedTime(Order order){
        order.setEstimatedTime(0); // for now
    }
}
