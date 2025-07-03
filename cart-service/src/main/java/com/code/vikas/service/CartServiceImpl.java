package com.code.vikas.service;

import com.code.vikas.entites.Cart;
import com.code.vikas.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart getCartById(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    @Override
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart updateCart(Long id, Cart cart) {
        Cart existingCart = getCartById(id);
        existingCart.setItems(cart.getItems());
        existingCart.setUpdatedAt(cart.getUpdatedAt());
        existingCart.setCreatedAt(cart.getCreatedAt());
        existingCart.setStatus(cart.getStatus());
        existingCart.setTotalPrice(cart.getTotalPrice());

        return cartRepository.save(existingCart);
    }

    @Override
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }
}
