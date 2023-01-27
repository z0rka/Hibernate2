package com.example.hibernate2;

import com.example.hibernate2.dto.ClientDto;
import com.example.hibernate2.service.CustomerService;
import com.example.hibernate2.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@Slf4j
public class Hibernate2Application {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ShopService shopService;

    public static void main(String[] args) {
        SpringApplication.run(Hibernate2Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        shopService.addProduct("Iphone 14", 1000, "New iphone!");
        shopService.addProduct("PowerBank", 100, "PowerBank!");
        shopService.addProduct("Laptop", 2000, "Laptop!");

        customerService.addClient("James", "james@gmail.com", "+38031631933");
        customerService.addClient("Emily", "emily@gmail.com", "+38034124193");
        customerService.addClient("Anton", "anton@gmail.com", "+38012421193");

        customerService.addAddress(4, "UA", "Kyiv", "Khreshatik", "40");
        customerService.addAddress(5, "USA", "New York", "Time square", "3");
        customerService.addAddress(6, "Ukraine", "Kyiv", "Peremogy", "23A");

        customerService.placeOrder(4);
        customerService.placeOrder(5);
        customerService.placeOrder(6);

        customerService.orderItem(10, "Iphone 14", 3);
        customerService.orderItem(11, "Laptop", 1);
        customerService.orderItem(12, "PowerBank", 2);

        ClientDto client1 = customerService.getFullInfo(4);
        log.info(client1.toString() + client1.getAddress().toString());
        client1.getOrdersHistory().forEach(order -> {
            log.info(order.toString());

            order.getOrderItems().forEach(orderItemDto -> log.info(orderItemDto.toString()));
        });

        ClientDto client2 = customerService.getClientOrdersId(5);
        log.info(client2.toString());
        client2.getOrdersHistory().forEach(order -> log.info(order.toString()));

        ClientDto client3 = customerService.getAddressInfo(6);
        log.info(client3.toString() + client3.getAddress().toString());


        customerService.changeAddress(4, "Ukraine", "Kharkiv", "Central", "30");

        ClientDto client4 = customerService.getAddressInfo(4);
        log.info(client4.toString() + client4.getAddress().toString());

        customerService.deleteClient(4);
        customerService.deleteOrder(11);

    }
}
