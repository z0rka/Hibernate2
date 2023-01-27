package com.example.hibernate2.service;

import com.example.hibernate2.model.Product;
import com.example.hibernate2.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShopService {
    protected final ObjectMapper objectMapper;
    private final ProductRepository productRepository;

    public void addProduct(String name, double price, String description) {
        if (name == null || description == null) {
            log.error("Name of description is null");
            return;
        }

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);

        productRepository.save(product);
    }

    public void deleteProduct(int id){
        productRepository.deleteById(id);
        log.info("Product deleted");
    }
}
