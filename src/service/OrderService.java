package service;

import model.Order;

import java.util.List;

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
}
