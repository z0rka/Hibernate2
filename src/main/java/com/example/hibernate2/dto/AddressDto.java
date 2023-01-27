package com.example.hibernate2.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private int id;

    private ClientDto client;

    private String country;

    private String city;

    private String street;

    private String house;
}
