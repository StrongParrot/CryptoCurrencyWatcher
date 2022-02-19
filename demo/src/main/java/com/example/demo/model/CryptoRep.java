package com.example.demo.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CryptoRep extends JpaRepository<Crypto,Long> {

    Optional<Crypto> findBySymbol(String symbol);
}
