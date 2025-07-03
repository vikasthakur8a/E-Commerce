package com.code.vikas.enities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private Long customerId;
    private LocalDate orderDate;
    private String orderStatus;
    private String shippingAddress;
    private String billingAddress;
    private Double totalAmount;
    private String paymentMethod;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;
}
