package com.code.vikas.service;

import com.code.vikas.component.OrderDetails;
import com.code.vikas.enities.Order;
import com.code.vikas.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    CompletableFuture<String> future = new CompletableFuture<>();

    @Override
    public List<OrderDetails> getAllOrders() {
        try{
            kafkaTemplate.send("product-details-request", "1");
            String js = future.get(10, TimeUnit.SECONDS);
            return orderRepository.findAll().stream().map(s -> new OrderDetails(s, js)).toList();
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Long id, Order order) {
        Order existingOrder = getOrderById(id);
        if(existingOrder == null){
            return null;
        }

        existingOrder.setOrderDate(order.getOrderDate());
        existingOrder.setOrderStatus(order.getOrderStatus());
        existingOrder.setItems(order.getItems());

        return orderRepository.save(existingOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @KafkaListener(topics = "product-details-response", groupId = "group-3")
    public void receiveProductDetailsResponse(String json){
        future.complete(json);
        System.out.println("json = " + json);
    }
}
