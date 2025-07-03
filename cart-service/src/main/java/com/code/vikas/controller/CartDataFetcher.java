package com.code.vikas.controller;

import com.code.vikas.entites.Cart;
import com.code.vikas.repository.CartRepository;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@DgsComponent
public class CartDataFetcher {

    @Autowired
    private CartRepository cartRepository;

    @DgsQuery
    public List<Cart> carts(@InputArgument Long idFilter){
        if(idFilter == null){
            return cartRepository.findAll();
        }
        return cartRepository.findAll().stream().filter(s ->(Objects.equals(s.getId(), idFilter))).collect(Collectors.toList());
    }
}
