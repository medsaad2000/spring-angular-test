package lu.atozdigital.api.service;

import lu.atozdigital.api.entity.Article;
import lu.atozdigital.api.entity.Order;

import java.util.List;

public interface OrderService {

    Order saveOrder(Order order);

    List<Order> getAllOrders();

    Order getOrderById(Long id);

}
