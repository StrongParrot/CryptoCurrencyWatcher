package com.example.demo.controller;

import com.example.demo.model.Client;
import com.example.demo.model.Crypto;
import com.example.demo.model.Notifier;
import com.example.demo.service.ClientService;
import com.example.demo.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@PropertySource("application.properties")
@RestController
public class Controller  {



    private final Notifier notifier;

    private final ClientService clientService;
    private final CryptoService cryptoService;

    @Autowired
    public Controller( ClientService clientService, CryptoService cryptoService,Notifier notifier) {
        this.clientService = clientService;
        this.cryptoService = cryptoService;
        this.notifier=notifier;
        cryptoService.create(new Crypto(90L,"BTC",cryptoService.getActualPrice("BTC")));
        cryptoService.create(new Crypto(80L,"ETH",cryptoService.getActualPrice("ETH")));
        cryptoService.create(new Crypto(48543L,"SOL",cryptoService.getActualPrice("SOL")));
    }


    @PostMapping(value = "/clients")
    public ResponseEntity<?> notify(@RequestBody Client client) {
        client.setPrice(cryptoService.getActualPrice(client.getCryptoName()));
        clientService.create(client);
        notifier.addNotify(client);
        return new ResponseEntity<>(HttpStatus.CREATED);}



    @GetMapping(value = "/clients")
    public ResponseEntity<List<Client>> readClients() {
        final List<Client> clients = clientService.readAll();
        return clients != null &&  !clients.isEmpty()
                    ? new ResponseEntity<>(clients, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    @GetMapping(value = "/cryptos")
    public ResponseEntity<List<Crypto>> readCryptos() {
        final List<Crypto> cryptos = cryptoService.readAll();
        return cryptos != null &&  !cryptos.isEmpty()
                ? new ResponseEntity<>(cryptos, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @GetMapping(value = "/clients/{id}")
    public ResponseEntity<Client> readClient(@PathVariable(name = "id") Long id) {
        final Optional<Client> client =  clientService.read(id);

        return client.isPresent()
                ? new ResponseEntity<>(client.get(),HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/cryptos/{symbol}")
    public ResponseEntity<Crypto> readCryptoSymbol(@PathVariable(name = "symbol") String symbol) {
        final Optional<Crypto> crypto = cryptoService.read(symbol);
        double price=crypto.get().getPrice();
        return crypto.isPresent()
                ? new ResponseEntity<Crypto>(crypto.get(),HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @PutMapping(value = "/clients/{id}")
    public ResponseEntity<?> updateClient(@PathVariable(name = "id") Long id, @RequestBody Client client) {
        final boolean updated = clientService.update(client, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    @PutMapping(value = "/cryptos/{id}")
    public ResponseEntity<?> updateCrypto(@PathVariable(name = "id") Long id, @RequestBody Crypto crypto) {
        final boolean updated = cryptoService.update(crypto, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }




    @DeleteMapping(value = "/clients/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable(name = "id") Long id) {
        notifier.removeNotify( clientService.read(id).get());
        final boolean deleted = clientService.delete(id);


        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    @DeleteMapping(value = "/crypto/{id}")
    public ResponseEntity<?> deleteCrypto(@PathVariable(name = "id") Long id) {
        final boolean deleted = cryptoService.delete(id);


        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }












}
