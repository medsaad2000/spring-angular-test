package lu.atozdigital.api.service;

import lu.atozdigital.api.entity.Article;
import lu.atozdigital.api.entity.Order;
import lu.atozdigital.api.repository.OrderRepository;
import lu.atozdigital.api.util.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository ;

    private OrderUtil orderUtil = new OrderUtil();
    @Override
    public Order saveOrder(Order order) {
        Date date = new Date();
        order.setDate(date);
        order.setReference(orderUtil.randomString(15));
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return (List<Order>)orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).get();
    }


}
