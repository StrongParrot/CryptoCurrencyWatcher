package com.example.demo.service;

import com.example.demo.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    void create(Client client);


    List<Client> readAll();


    Optional<Client> read(Long id);


    boolean update(Client client, Long id);


    boolean delete(Long id);
}

