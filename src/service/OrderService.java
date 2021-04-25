package service;

import model.Order;
import model.products.Product;


public final class OrderService {
    public OrderService() {}

//  CALC TOTAL PRICE
    public void calcTotalPrice(Order order){
        double totalPrice = order.getProducts()
                .stream()
                .mapToDouble(Product::getPrice)
                .sum();
        order.setTotalPrice(totalPrice);
    }
}
