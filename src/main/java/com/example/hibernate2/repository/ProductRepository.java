package com.example.hibernate2.repository;

import com.example.hibernate2.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    Optional<Product> findFirstByName(String name);
}
