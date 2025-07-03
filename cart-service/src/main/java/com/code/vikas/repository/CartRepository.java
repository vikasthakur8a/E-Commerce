package com.code.vikas.repository;

import com.code.vikas.entites.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
