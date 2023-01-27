package com.example.hibernate2.repository;

import com.example.hibernate2.model.OrderItem;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository  extends CrudRepository<OrderItem,Integer> {
}
