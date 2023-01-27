package com.example.hibernate2.service;

import com.example.hibernate2.dto.*;
import com.example.hibernate2.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service that converts into DTO
 */
@Service
@RequiredArgsConstructor
public class ConvertIntoDtoService {

    public static final String CLIENT_NOT_FOUND_EXISTS = "Client not found/exists";
    public final ObjectMapper objectMapper;

    /**
     * Method converts {@link Address} to {@link AddressDto}
     *
     * @param client - client
     * @return AddressDto
     */

    public AddressDto convertAddress(Optional<Client> client) {
        return objectMapper.convertValue(
                client
                        .orElseThrow(() -> new EntityNotFoundException(CLIENT_NOT_FOUND_EXISTS)).getAddress(),
                AddressDto.class);
    }

    /**
     * Method converts {@link Client} to {@link ClientDto}
     *
     * @param client - client
     * @return ClientDto
     */
    public ClientDto convertClient(Optional<Client> client) {
        return objectMapper
                .convertValue(client
                                .orElseThrow(() -> new EntityNotFoundException(CLIENT_NOT_FOUND_EXISTS)),
                        ClientDto.class);
    }


    /**
     * Method converts
     * 1.{@link Order} into {@link OrderDto}
     * 2.{@link OrderItem} into {@link OrderItemDto}
     * 3.{@link Product} into {@link ProductDto}
     *
     * @param client - client
     * @return List<OrderDto>
     */
    public List<OrderDto> fullConvertOrders(Optional<Client> client) {
        return client
                .orElseThrow(() -> new EntityNotFoundException(CLIENT_NOT_FOUND_EXISTS))
                .getOrdersHistory().stream().map(order -> {
                    OrderDto orderDto = objectMapper.convertValue(order, OrderDto.class);

                    List<OrderItemDto> orderList = order.getOrderItems().stream().map(orderItem -> {
                        OrderItemDto orderItemDto = objectMapper.convertValue(orderItem, OrderItemDto.class);
                        ProductDto productDto = objectMapper.convertValue(orderItem.getProduct(), ProductDto.class);
                        orderItemDto.setProduct(productDto);
                        return orderItemDto;
                    }).toList();

                    orderDto.setOrderItems(orderList);
                    return orderDto;
                }).toList();
    }

    /**
     * Method converts
     * 1.{@link Order} into {@link OrderDto}
     *
     * @param client - client
     * @return List<OrderDto>
     */

    public List<OrderDto> convertOrders(Optional<Client> client) {
        return client
                .orElseThrow(() -> new EntityNotFoundException(CLIENT_NOT_FOUND_EXISTS))
                .getOrdersHistory().stream().map(order -> objectMapper.convertValue(order, OrderDto.class)).toList();
    }

    /**
     * Method converts
     * 1.{@link Product} into {@link ProductDto}
     *
     * @param product - product
     * @return {@link ProductDto}
     */
    public ProductDto convertProduct(Optional<Product> product) {
        return objectMapper.convertValue(product
                .orElseThrow(() -> new EntityNotFoundException("Product is null")), ProductDto.class);
    }
}
