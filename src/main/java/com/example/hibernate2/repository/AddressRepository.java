package com.example.hibernate2.repository;

import com.example.hibernate2.model.Address;
import com.example.hibernate2.model.Client;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer> {

    @Modifying
    @Query("update Address a set a.country =?2 , a.city =?3,a.street=?4,a.house=?5 where a.client=?1")
    void update(Client client, String country, String city, String street, String house);
}
