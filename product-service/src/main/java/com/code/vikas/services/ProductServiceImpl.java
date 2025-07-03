package com.code.vikas.services;

import com.code.vikas.entities.Product;
import com.code.vikas.repositories.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        Product existingProduct =getProductById(productId);
        if(existingProduct == null){
            return null;
        }
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setUpdatedAt(product.getUpdatedAt());
        existingProduct.setCreatedAt(product.getCreatedAt());
        existingProduct.setImageUrl(product.getImageUrl());
        existingProduct.setStockQuantity(product.getStockQuantity());

        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @KafkaListener(topics = "product-details-request", groupId = "group-3")
    public void receiveProductDetailsRequest(Long productId) throws JsonProcessingException {

        Product product = this.getProductById(productId);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(product);

        kafkaTemplate.send("product-details-request", json);
    }
}
