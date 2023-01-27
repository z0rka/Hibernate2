package com.example.hibernate2.dto;

import jakarta.persistence.*;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    private int id;

    private OrderDto order;

    private ProductDto product;

    private int count;
}
