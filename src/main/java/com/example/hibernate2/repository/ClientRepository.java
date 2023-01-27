package com.example.hibernate2.repository;

import com.example.hibernate2.model.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Integer> {

}
