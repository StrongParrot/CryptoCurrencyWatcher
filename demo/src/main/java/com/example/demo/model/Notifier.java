package com.example.demo.model;

import com.example.demo.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

@Component
@Scope("singleton")
public class Notifier extends Thread {
    private final LinkedList<Client> clients=new LinkedList<>();
    private final CryptoService cryptoService;
    org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Notifier.class);
    @Autowired
    public Notifier(CryptoService cryptoService) {
        this.cryptoService=cryptoService;
        this.start();

    }

    @Override
    @Async
    public void run(){
        while (!isInterrupted()){try {
           updateCryptos(cryptoService.readAll());
            if (clients.isEmpty()){

                sleep(60000);
            continue;}



                for (int i = 0; i < clients.size(); i++) {
                    Client client = clients.get(i);
                    String cryptoName = client.getCryptoName();
                    double actualPrice=cryptoService.read(client.getCryptoName()).get().getPrice();
                    if (getChange(client.getPrice(),actualPrice)>1){
                      logger.warn("{} {} {}",client.getUsername(),cryptoName,getChange(client.getPrice(),actualPrice));
                    }
                }
                sleep(60000);

        }
        catch (InterruptedException e) {
            e.printStackTrace(); }
    }}

    public void addNotify(Client client){
        if (clients.contains(client))
            return;
        clients.add(client);
    }
    public void removeNotify(Client client){
        if (!clients.contains(client))
            return;
        clients.remove(client);
        if (clients.isEmpty())
            interrupt();
    }

    private  int getChange(double v1,double v2){
        return (int) ( (100*(Math.max(v1,v2)-Math.min(v1, v2))/v1));
    }


    private synchronized void updateCryptos(List<Crypto> list){
        for (Crypto c :
                list) {
            c.setPrice(cryptoService.setActualPrice(c.getSymbol()));
            cryptoService.update(c, c.getId());
        }
    }
}
