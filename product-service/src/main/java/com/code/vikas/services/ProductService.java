package com.code.vikas.services;

import com.code.vikas.entities.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(Long productId);

    Product createProduct(Product product);

    Product updateProduct(Long productId, Product product);

    void deleteProduct(Long productId);

}
