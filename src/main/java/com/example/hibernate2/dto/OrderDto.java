package com.example.hibernate2.dto;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {


    private int id;


    private ClientDto client;

    List<OrderItemDto> orderItems;
}
