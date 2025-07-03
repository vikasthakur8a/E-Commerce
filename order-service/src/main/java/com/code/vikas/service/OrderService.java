package com.code.vikas.service;

import com.code.vikas.component.OrderDetails;
import com.code.vikas.enities.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    List<OrderDetails> getAllOrders();
    Order getOrderById(Long id);
    Order createOrder(Order order);
    Order updateOrder(Long id, Order order);
    void deleteOrder(Long id);
}
