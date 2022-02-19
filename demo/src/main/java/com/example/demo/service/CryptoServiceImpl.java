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
    @Autowired
    public CryptoServiceImpl(CryptoRep cryptoRep) {
        this.cryptoRep = cryptoRep;
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
    public double  getActualPrice(String name){
        RestTemplate answer=new RestTemplate();
        String map="";

        if(name.equals("BTC")){

            String URLBTC = "https://api.coinlore.net/api/ticker/?id=90";
            map=(answer.getForObject(URLBTC,map.getClass()));
            map=map.replaceAll(",",":");
            map=map.replaceAll("\""," ");
            String[] split=map.split(":");
            map=split[11];
            return Double.parseDouble(map);
        }
        if(name.equals("ETH")){

            String URLETH = "https://api.coinlore.net/api/ticker/?id=80";
            map=(answer.getForObject(URLETH,map.getClass()));
            map=map.replaceAll(",",":");
            map=map.replaceAll("\""," ");
            String[] split=map.split(":");

            map=split[11];
            return Double.parseDouble(map);
        }


        String URLSOL = "https://api.coinlore.net/api/ticker/?id=48543";
        map=(answer.getForObject(URLSOL,map.getClass()));
        map=map.replaceAll(",",":");
        map=map.replaceAll("\""," ");
        String[] split=map.split(":");

        map=split[11];

        return Double.parseDouble(map);
    }
}
