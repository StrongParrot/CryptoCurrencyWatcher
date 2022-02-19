package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
public class Crypto {
    @Id
    @Column
    private Long id;
    @Column
    private String symbol;
    @Column
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }



    public Crypto() {
    }

    public Crypto(Long id, String symbol, double price) {
        this.id = id;
        this.symbol = symbol;
        this.price = price;
    }
}