package com.example.hibernate2.dto;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private int id;

    private String name;

    private String description;

    private double price;


    private List<OrderItemDto> orderItems;
}
