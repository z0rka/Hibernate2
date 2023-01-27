package com.example.hibernate2.repository;

import com.example.hibernate2.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository  extends CrudRepository<Order,Integer> {
}
