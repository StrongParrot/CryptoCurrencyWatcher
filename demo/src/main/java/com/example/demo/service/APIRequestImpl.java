package com.example.demo.service;

import com.example.demo.model.Crypto;
import org.springframework.stereotype.Component;

@Component
public class APIRequestImpl implements APIRequest{
    @Override
    public Crypto[] getCrypto(int idNum) {
        return null;
    }
}
