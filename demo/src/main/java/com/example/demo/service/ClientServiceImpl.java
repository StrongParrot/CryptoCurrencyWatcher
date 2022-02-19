package com.example.demo.service;

import com.example.demo.model.Client;
import com.example.demo.model.ClientRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService{
    private final ClientRep clientRep;
    @Autowired
    public ClientServiceImpl(ClientRep clientRep) {
        this.clientRep = clientRep;
    }


    @Override
    public void create(Client client) {
        clientRep.save(client);

    }

    @Override
    public List<Client> readAll() {
        return  clientRep.findAll();
    }


    @Override
    public Optional<Client> read(Long id) {
        return clientRep.findById(id);
    }

    @Override
    public boolean update(Client client, Long id) {
        if (clientRep.existsById(id)) {
            client.setId(id);
            clientRep.save(client);
            return true;
        }
    return false;

    }

    @Override
    public boolean delete(Long id) {
        if (clientRep.existsById(id)) {
            clientRep.deleteById(id);
            return true;
        }
        return false;
    }
}
