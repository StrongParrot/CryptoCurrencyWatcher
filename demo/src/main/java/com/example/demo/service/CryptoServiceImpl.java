package com.example.demo.service;

import com.example.demo.model.Crypto;
import com.example.demo.model.CryptoRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
@Service
public class CryptoServiceImpl implements CryptoService{
    private final CryptoRep cryptoRep;
    private final APIRequest apiRequest;
    @Autowired
    public CryptoServiceImpl(CryptoRep cryptoRep,APIRequest apiRequest) {
        this.cryptoRep = cryptoRep;
        this.apiRequest =apiRequest;
    }

    @Override
    public void create(Crypto crypto) {
        cryptoRep.save(crypto);
    }

    @Override
    public List<Crypto> readAll() {
        return cryptoRep.findAll();
    }

    @Override
    public Optional<Crypto> read(String symbol) {
        return cryptoRep.findBySymbol(symbol);
    }


    @Override
    public boolean update(Crypto crypto, Long id) {
        if (cryptoRep.existsById(id)) {
            crypto.setId(id);
            cryptoRep.save(crypto);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if (cryptoRep.existsById(id)) {
            cryptoRep.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public double setActualPrice(String cryptoName){
        if(cryptoName.equals("BTC")) {
            Crypto[] crypto = apiRequest.getCrypto(90);
            return crypto[0].getPrice();
        }
        if (cryptoName.equals("ETH")){
            Crypto[] crypto=apiRequest.getCrypto(80);
            return crypto[0].getPrice();
        }

        Crypto[] crypto=apiRequest.getCrypto(48543);
        return crypto[0].getPrice();
    }
}
