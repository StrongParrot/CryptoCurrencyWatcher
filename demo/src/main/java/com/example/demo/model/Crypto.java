package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.stereotype.Component;

import javax.persistence.*;
@JsonAutoDetect
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
    @JsonCreator
    public Crypto(@JsonProperty("id") Long id,
                  @JsonProperty("symbol") String symbol,
                  @JsonProperty("price_usd") double price) {
        this.id = id;
        this.symbol = symbol;
        this.price = price;
    }
}