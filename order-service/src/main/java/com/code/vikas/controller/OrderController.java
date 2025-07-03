package com.code.vikas.controller;

import com.code.vikas.component.OrderDetails;
import com.code.vikas.enities.Order;
import com.code.vikas.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("")
    public List<OrderDetails> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id){
        return orderService.getOrderById(id);
    }

    @PostMapping("")
    public Order createOrder(@RequestBody Order order){
        return orderService.createOrder(order);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order order){
        return orderService.updateOrder(id, order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
    }

}
