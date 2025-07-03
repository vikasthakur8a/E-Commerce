package com.code.vikas.controller;

import com.code.vikas.entites.Cart;
import com.code.vikas.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("")
    public List<Cart> getAllCarts(){
        return cartService.getAllCarts();
    }

    @GetMapping("/{id}")
    public Cart getCartById(@PathVariable Long id){
        return cartService.getCartById(id);
    }

    @PostMapping("")
    public Cart createCart(@RequestBody Cart Cart){
        return cartService.createCart(Cart);
    }

    @PutMapping("/{id}")
    public Cart updateCart(@PathVariable Long id, @RequestBody Cart Cart){
        return cartService.updateCart(id, Cart);
    }

    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable Long id){
        cartService.deleteCart(id);
    }

}