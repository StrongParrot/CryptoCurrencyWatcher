package com.example.demo.service;

import com.example.demo.model.Crypto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(name="api.coinlore.net", url="https://api.coinlore.net", path = "/api/ticker" )
public interface APIRequest {
    @RequestMapping(method = GET,value = "/",consumes = APPLICATION_JSON_VALUE)
    Crypto[] getCrypto(@RequestParam("id")int idNum);
}
