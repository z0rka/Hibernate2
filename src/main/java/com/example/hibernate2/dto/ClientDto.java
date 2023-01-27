package com.example.hibernate2.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private int id;

    private String name;

    private String email;

    private String phone;

    private AddressDto address;

    private List<OrderDto> ordersHistory;
}
