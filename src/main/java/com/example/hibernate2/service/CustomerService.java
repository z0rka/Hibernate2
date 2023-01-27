package com.example.hibernate2.service;

import com.example.hibernate2.dto.*;
import com.example.hibernate2.model.*;
import com.example.hibernate2.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service for customer
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final OrderItemRepository orderItemRepository;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;
    private final ConvertIntoDtoService convertIntoDtoService;

    /**
     * Method returns clients orders(without products)
     *
     * @param clientId - id
     * @return {@link ClientDto}
     */
    public ClientDto getClientOrdersId(int clientId) {
        Optional<Client> client = clientRepository.findById(clientId);

        ClientDto clientDto = convertIntoDtoService.convertClient(client);
        clientDto.setOrdersHistory(convertIntoDtoService.convertOrders(client));

        return clientDto;
    }

    /**
     * Method returns clients info
     *
     * @param clientId - id
     * @return {@link ClientDto}
     */
    public ClientDto getAddressInfo(int clientId) {
        Optional<Client> client = clientRepository.findById(clientId);

        ClientDto clientDto = convertIntoDtoService.convertClient(client);

        AddressDto addressDto = convertIntoDtoService.convertAddress(client);

        clientDto.setAddress(addressDto);
        return clientDto;
    }

    /**
     * Method gives all info about client
     *
     * @param clientId - id
     * @return {@link ClientDto} - client info
     */
    public ClientDto getFullInfo(int clientId) {
        Optional<Client> client = clientRepository.findById(clientId);
        ClientDto clientDto = convertIntoDtoService.convertClient(client);

        AddressDto addressDto = convertIntoDtoService.convertAddress(client);

        List<OrderDto> orders = convertIntoDtoService.fullConvertOrders(client);

        clientDto.setOrdersHistory(orders);
        clientDto.setAddress(addressDto);

        return clientDto;
    }


    /**
     * Changing address of the client
     *
     * @param clientId - id
     * @param country  - country
     * @param city     -city
     * @param street   - street
     * @param house    - house
     */
    @Transactional
    public void changeAddress(int clientId, String country, String city, String street, String house) {
        if (country == null || city == null || street == null || house == null) {
            log.error("Not correct data!");
            return;
        }

        Optional<Client> client = clientRepository.findById(clientId);
        addressRepository.update(
                client
                        .orElseThrow(() -> new EntityNotFoundException("Client " + clientId + " not found/exists")),
                country, city, street, house);
    }

    /**
     * Add new item to the order
     *
     * @param orderId  - order of the id
     * @param amount   - amount of items
     * @param itemName - name of the item
     */
    public void orderItem(int orderId, String itemName, int amount) {
        if (itemName == null) {
            log.error("Item name is null!");
        }

        Optional<Order> order = orderRepository.findById(orderId);
        Optional<Product> product = productRepository.findFirstByName(itemName);


        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order
                .orElseThrow(() -> new EntityNotFoundException("Order with id " + orderId + " not found")));
        orderItem.setProduct(product
                .orElseThrow(() -> new EntityNotFoundException("Product with name " + itemName + " not found")));

        orderItem.setCount(amount);

        orderItemRepository.save(orderItem);
    }

    /**
     * Place new order
     *
     * @param clientId - id
     */
    public void placeOrder(int clientId) {
        Optional<Client> client = clientRepository.findById(clientId);
        Order order = new Order();
        order.setClient(client
                .orElseThrow(() -> new EntityNotFoundException("Client with id " + clientId + " not exists")));

        orderRepository.save(order);
    }

    /**
     * Add new address
     *
     * @param clientId - id
     * @param country  - country
     * @param city     -city
     * @param street   - street
     * @param house    - house
     */
    public void addAddress(int clientId, String country, String city, String street, String house) {
        if (country == null || city == null || street == null || house == null) {
            log.error("Not correct data!");
            return;
        }

        Optional<Client> client = clientRepository.findById(clientId);

        Address address = new Address();
        address.setClient(client
                .orElseThrow(() -> new EntityNotFoundException("Client with id " + clientId + " not exists")));
        address.setCountry(country);
        address.setCity(city);
        address.setStreet(street);
        address.setHouse(house);

        addressRepository.save(address);

    }

    /**
     * Create new client
     *
     * @param name  - name
     * @param email - email
     * @param phone - phone
     */
    public void addClient(String name, String email, String phone) {
        if (name == null || email == null || phone == null) {
            log.error("Not correct data!");
            return;
        }

        Client client = new Client();
        client.setName(name);
        client.setEmail(email);
        client.setPhone(phone);

        clientRepository.save(client);

        log.info("New client added!");
    }

    /**
     * Method to delete client
     */
    @Transactional
    public void deleteClient(int clientId) {
        clientRepository.deleteById(clientId);
        log.info("Client " + clientId + " deleted");
    }

    /**
     * Method to delete order
     */
    @Transactional
    public void deleteOrder(int orderId) {
        orderRepository.deleteById(orderId);
        log.info("Order " + orderId + " was deleted");
    }

    /**
     * Method to delete OrderItem
     */
    @Transactional
    public void deleteOrderItem(int id) {
        orderItemRepository.deleteById(id);
    }
}
