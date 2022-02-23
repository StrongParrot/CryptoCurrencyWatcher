package com.example.demo.service;

import com.example.demo.model.Client;
import com.example.demo.model.Crypto;

import java.util.List;
import java.util.Optional;

public interface CryptoService {


    void create(Crypto crypto);


    List<Crypto> readAll();


    Optional<Crypto> read(String symbol);


    boolean update(Crypto crypto, Long id);

    boolean delete(Long id);

    double setActualPrice(String cryptoName);
}
