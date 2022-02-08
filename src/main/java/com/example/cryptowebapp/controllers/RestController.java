package com.example.cryptowebapp.controllers;



import com.example.cryptowebapp.models.CoinData;
import com.example.cryptowebapp.repositories.JsonRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    JsonRepository jsonRepository;

    @GetMapping("/refreshedstats")
    public String refresh(){
        List<CoinData> coinDataList = jsonRepository.findAll().subList((int)(jsonRepository.count() -10), (int) jsonRepository.count());
        String json = new Gson().toJson(coinDataList);
        return json;
    }
}
