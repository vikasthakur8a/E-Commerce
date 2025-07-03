package com.code.vikas.service;

import com.code.vikas.entites.Cart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {

    List<Cart> getAllCarts();
    Cart getCartById(Long id);
    Cart createCart(Cart cart);
    Cart updateCart(Long id, Cart cart);
    void deleteCart(Long id);
}
